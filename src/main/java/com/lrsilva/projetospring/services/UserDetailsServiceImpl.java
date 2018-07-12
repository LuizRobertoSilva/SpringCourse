package com.lrsilva.projetospring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lrsilva.projetospring.domain.Client;
import com.lrsilva.projetospring.repositories.ClientRepository;
import com.lrsilva.projetospring.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private ClientRepository clientRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Client cli = clientRepository.findByEmail(email);
		if (cli == null)
			throw new UsernameNotFoundException(email);

		return new UserSS(cli.getId(), cli.getPassword(), cli.getEmail(), cli.getProfile());
	}

}
