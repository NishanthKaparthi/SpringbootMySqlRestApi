package com.springboot.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.app.model.Employee;

//The JpaRepository interface defines methods for all the CRUD operations on the entity, 
//and a default implementation of the JpaRepository called SimpleJpaRepository


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	Optional<Employee> findByEmail(String email);
	Optional<Employee> findByFirstName(String firstName);
	
	
}
