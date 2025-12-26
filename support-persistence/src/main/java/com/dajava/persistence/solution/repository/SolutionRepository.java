package com.dajava.persistence.solution.repository;

import java.util.Optional;
import org.springframework.stereotype.Repository;
import com.dajava.api.domain.register.entity.Register;
import com.dajava.api.domain.solution.entity.Solution;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface SolutionRepository extends JpaRepository<Solution, Long>{
	Optional<Solution> findByRegister(Register register);
}