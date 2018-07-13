package com.lrsilva.projetospring.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.lrsilva.projetospring.domain.Address;
import com.lrsilva.projetospring.domain.City;
import com.lrsilva.projetospring.domain.Client;
import com.lrsilva.projetospring.domain.enums.ClientType;
import com.lrsilva.projetospring.domain.enums.Profile;
import com.lrsilva.projetospring.dto.ClientDTO;
import com.lrsilva.projetospring.dto.ClientNewDTO;
import com.lrsilva.projetospring.repositories.AddressRepository;
import com.lrsilva.projetospring.repositories.ClientRepository;
import com.lrsilva.projetospring.security.UserSS;
import com.lrsilva.projetospring.services.exceptions.AuthorizationException;
import com.lrsilva.projetospring.services.exceptions.DataIntegrityException;
import com.lrsilva.projetospring.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repo;
	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private BCryptPasswordEncoder be;
	@Autowired
	private S3Service s3Service;
	@Autowired
	private ImageService imageService;

	@Value("${img.prefix.client.profile}")
	private String prefix;
	
	@Value("${img.profile.size}")
	private Integer size;

	public Client find(Integer id) {

		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Profile.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Access Denied");
		}

		Optional<Client> obj = repo.findById(id);
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("Object not found! Id: " + id + ", Type:" + Client.class.getName()));
	}

	public List<Client> findAll() {
		List<Client> obj = repo.findAll();
		return obj;
	}

	public Page<Client> findByPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Client findByEmail(String email) {
		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Profile.ADMIN) && !email.equals(user.getUsername())) {
			throw new AuthorizationException("Access Denied");
		}
		Client obj = find(user.getId());
		if(obj == null) {
			throw new ObjectNotFoundException("Object not found ID:" + user.getId());
		}
		return obj;
	}

	@Transactional
	public Client insert(Client obj) {
		obj.setId(null);
		obj = repo.save(obj);
		addressRepository.saveAll(obj.getAddresses());
		return obj;
	}

	public Client update(Client obj) {
		Client newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("It's not possible delete a client with entities associate");
		}
	}

	public Client fromDTO(ClientDTO objDTO) {
		return new Client(objDTO.getId(), objDTO.getName(), objDTO.getEmail(), null, null, null);
	}

	public Client fromDTO(ClientNewDTO objDTO) {

		Client cli = new Client(null, objDTO.getName(), objDTO.getEmail(), objDTO.getCpfOrCnpj(),
				ClientType.toEnum(objDTO.getType()), be.encode(objDTO.getPassword()));

		City city = new City(objDTO.getCityId(), null, null);

		Address ad = new Address(null, objDTO.getStreet(), objDTO.getNumber(), objDTO.getComplement(),
				objDTO.getNeighbourhood(), objDTO.getZipCode(), cli, city);

		cli.getAddresses().addAll(Arrays.asList(ad));

		cli.getTelephones().add(objDTO.getTelphone1());
		if (objDTO.getTelphone2() != null) {
			cli.getTelephones().add(objDTO.getTelphone2());

		}
		if (objDTO.getTelphone3() != null) {
			cli.getTelephones().add(objDTO.getTelphone3());
		}

		return cli;
	}

	private void updateData(Client newObj, Client obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getName());
	}

	public URI uploadProfilePicture(MultipartFile multipartFile) {

		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Access Denied");
		}

		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
		jpgImage = imageService.cropSquare(jpgImage);
		jpgImage = imageService.resize(jpgImage, size);
		String fileName = prefix + user.getId() + ".jpg";

		return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
	}

}
