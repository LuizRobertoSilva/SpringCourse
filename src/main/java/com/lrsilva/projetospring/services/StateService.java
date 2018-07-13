package com.lrsilva.projetospring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lrsilva.projetospring.domain.State;
import com.lrsilva.projetospring.repositories.StateRepository;

@Service
public class StateService {

	@Autowired
	private StateRepository stateRepository;

	public List<State> findAll() {
		return stateRepository.findAllByOrderByName();
	}

}
