package com.lrsilva.projetospring.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lrsilva.projetospring.domain.OrderT;
import com.lrsilva.projetospring.services.OrderService;

@RestController
@RequestMapping(value = "/orders")
public class OrderResource {

	@Autowired
	private OrderService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<OrderT> find(@PathVariable Integer id) {
		OrderT obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody OrderT obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<OrderT>> findByPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "1") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "instant") String orderBy,
			@RequestParam(value = "direction", defaultValue = "DESC") String direction) {
		Page<OrderT> list = service.findPage(page, linesPerPage, orderBy, direction);

		return ResponseEntity.ok().body(list);
	}

}
