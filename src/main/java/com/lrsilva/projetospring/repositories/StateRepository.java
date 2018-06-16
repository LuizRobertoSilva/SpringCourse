package com.lrsilva.projetospring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lrsilva.projetospring.domain.State;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {

}
