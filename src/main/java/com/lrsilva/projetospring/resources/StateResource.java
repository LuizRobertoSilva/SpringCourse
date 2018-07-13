package com.lrsilva.projetospring.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lrsilva.projetospring.domain.City;
import com.lrsilva.projetospring.domain.State;
import com.lrsilva.projetospring.dto.CityDTO;
import com.lrsilva.projetospring.dto.StateDTO;
import com.lrsilva.projetospring.services.CityService;
import com.lrsilva.projetospring.services.StateService;

@RestController
@RequestMapping(value = "/states")
public class StateResource {

	@Autowired
	private StateService stateService;
	
	@Autowired
	private CityService cityService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<StateDTO>> findAll() {
		List<State> list = stateService.findAll();
		List<StateDTO> listDTO = list.stream().map(obj -> new StateDTO(obj)).collect(Collectors.toList());

		return ResponseEntity.ok().body(listDTO);
	}
	@RequestMapping(value="/{stateId}/cities", method = RequestMethod.GET)
	public ResponseEntity<List<CityDTO>> findCities(@PathVariable Integer stateId) {
		List<City> list = cityService.findByState(stateId);
		List<CityDTO> listDTO = list.stream().map(obj -> new CityDTO(obj)).collect(Collectors.toList());

		return ResponseEntity.ok().body(listDTO);
	}
}
