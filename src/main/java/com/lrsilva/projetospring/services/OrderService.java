package com.lrsilva.projetospring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lrsilva.projetospring.domain.OrderT;
import com.lrsilva.projetospring.repositories.OrderRepository;
import com.lrsilva.projetospring.services.exceptions.ObjectNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repo;

	public OrderT find(Integer id) {
		Optional<OrderT> obj = repo.findById(id);
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("Object not found! Id: " + id + ", Type:" + OrderT.class.getName()));
	}

	public List<OrderT> findAll() {
		List<OrderT> obj = repo.findAll();
		return obj;
	}

}
