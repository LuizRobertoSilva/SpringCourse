package com.lrsilva.projetospring.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lrsilva.projetospring.domain.Client;
import com.lrsilva.projetospring.repositories.ClientRepository;
import com.lrsilva.projetospring.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private BCryptPasswordEncoder be;

	@Autowired
	private EmailService emailService;

	private Random rand = new Random();

	public void sendNewPassword(String email) {
		Client client = clientRepository.findByEmail(email);
		if (client == null) {
			throw new ObjectNotFoundException("Email not found");
		}
		String newPass = newPassword();
		client.setPassword(be.encode(newPass));
		clientRepository.save(client);
		emailService.sendNewPasswordEmail(client, newPass);
	}

	private String newPassword() {
		char[] vet = new char[10];
		for (int i = 0; i < 10; i++) {
			vet[i] = randomChar();
		}
		return vet.toString();
	}

	private char randomChar() {
		int opt = rand.nextInt(3);
		if (opt == 0) {
			return (char) (rand.nextInt(10) + 48);
		} else if (opt == 1) {

			return (char) (rand.nextInt(26) + 65);
		} else if (opt == 2) {

			return (char) (rand.nextInt(26) + 97);
		}
		return 3;
	}
}
