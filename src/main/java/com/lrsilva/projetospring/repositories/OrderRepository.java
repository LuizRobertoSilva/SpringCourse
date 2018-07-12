package com.lrsilva.projetospring.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lrsilva.projetospring.domain.Client;
import com.lrsilva.projetospring.domain.OrderT;

@Repository
public interface OrderRepository extends JpaRepository<OrderT, Integer> {

	@Transactional
	Page<OrderT> findByClient(Client client, Pageable pageRequest);
}
