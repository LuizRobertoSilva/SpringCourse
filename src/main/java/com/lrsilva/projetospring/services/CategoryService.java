package com.lrsilva.projetospring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lrsilva.projetospring.domain.Category;
import com.lrsilva.projetospring.repositories.CategoryRepository;
import com.lrsilva.projetospring.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repo;

	public Category find(Integer id) {
		Optional<Category> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found! Id: " + id + ", Type:" + Category.class.getName()));
	}
	
	public List<Category> findAll(){
		List<Category> obj = repo.findAll();
		return obj;
	}

}
