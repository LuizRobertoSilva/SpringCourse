package com.lrsilva.projetospring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.lrsilva.projetospring.domain.Category;
import com.lrsilva.projetospring.domain.OrderT;
import com.lrsilva.projetospring.domain.Product;
import com.lrsilva.projetospring.repositories.CategoryRepository;
import com.lrsilva.projetospring.repositories.ProductRepository;
import com.lrsilva.projetospring.services.exceptions.ObjectNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repo;

	@Autowired
	private CategoryRepository categoryRepository;
	
	public Product find(Integer id) {
		Optional<Product> obj = repo.findById(id);
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("Object not found! Id: " + id + ", Type:" + OrderT.class.getName()));
	}

	public List<Product> findAll() {
		List<Product> obj = repo.findAll();
		return obj;
	}

	public Page<Product> search(String name, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy,
			String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Category> categories = categoryRepository.findAllById(ids);
		return repo.search(name, categories, pageRequest);
	}

}
