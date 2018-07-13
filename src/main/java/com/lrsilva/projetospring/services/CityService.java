package com.lrsilva.projetospring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lrsilva.projetospring.domain.City;
import com.lrsilva.projetospring.repositories.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository cityRepository;

	public List<City> findByState(Integer stateId) {
		return cityRepository.findCities(stateId);
	}

}
