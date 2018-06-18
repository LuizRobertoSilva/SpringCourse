package com.lrsilva.projetospring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lrsilva.projetospring.domain.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
