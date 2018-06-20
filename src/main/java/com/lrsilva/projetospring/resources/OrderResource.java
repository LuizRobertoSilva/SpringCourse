package com.lrsilva.projetospring.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lrsilva.projetospring.domain.OrderT;
import com.lrsilva.projetospring.services.OrderService;

@RestController
@RequestMapping(value = "/orders")
public class OrderResource {

	@Autowired
	private OrderService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		OrderT obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<?> find() {
		List<OrderT> obj = service.findAll();
		return ResponseEntity.ok().body(obj);
	}
}
