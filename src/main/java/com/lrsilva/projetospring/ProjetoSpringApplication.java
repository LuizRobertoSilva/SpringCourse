package com.lrsilva.projetospring;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lrsilva.projetospring.domain.Address;
import com.lrsilva.projetospring.domain.Category;
import com.lrsilva.projetospring.domain.City;
import com.lrsilva.projetospring.domain.Client;
import com.lrsilva.projetospring.domain.Product;
import com.lrsilva.projetospring.domain.State;
import com.lrsilva.projetospring.domain.enums.ClientType;
import com.lrsilva.projetospring.repositories.AddressRepository;
import com.lrsilva.projetospring.repositories.CategoryRepository;
import com.lrsilva.projetospring.repositories.CityRepository;
import com.lrsilva.projetospring.repositories.ClientRepository;
import com.lrsilva.projetospring.repositories.ProductRepository;
import com.lrsilva.projetospring.repositories.StateRepository;

@SpringBootApplication
public class ProjetoSpringApplication implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private StateRepository stateRepository;
	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private AddressRepository addressRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProjetoSpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category cat1 = new Category(null, "IT");
		Category cat2 = new Category(null, "Office");

		Product p1 = new Product(null, "Computer", 2000.00);
		Product p2 = new Product(null, "Printer", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);

		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2));

		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));

		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
		productRepository.saveAll(Arrays.asList(p1, p2, p3));

		State est1 = new State(null, "Minas Gerais");
		State est2 = new State(null, "São Paulo");

		City c1 = new City(null, "Ubelândia", est1);
		City c2 = new City(null, "São Paulo", est2);
		City c3 = new City(null, "Campinas", est2);

		est1.getCities().addAll(Arrays.asList(c1));
		est2.getCities().addAll(Arrays.asList(c2, c3));

		stateRepository.saveAll(Arrays.asList(est1, est2));
		cityRepository.saveAll(Arrays.asList(c1, c2, c3));

		Client cli1 = new Client(null, "Maria Silva", "maria@gmail.com", "31245685423", ClientType.PHYSICALPERSON);

		cli1.getTelephones().addAll(Arrays.asList("2723562353", "938445016"));

		Address ad1 = new Address(null, "Rua Flores", "300", "Apto 303", "Jardim", "382208834", cli1, c1);
		Address ad2 = new Address(null, "Avenida Matos", "105", "Sala 800", "Centro", "326541522", cli1, c2);

		cli1.getAddresses().addAll(Arrays.asList(ad1, ad2));

		clientRepository.saveAll(Arrays.asList(cli1));
		addressRepository.saveAll(Arrays.asList(ad1, ad2));
	}
}
