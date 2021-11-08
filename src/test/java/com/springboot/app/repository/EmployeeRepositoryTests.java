package com.springboot.app.repository;
/*
 * CRUD JUnit Tests for Spring Data JPA - Testing Repository Layer
 */
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;

import com.springboot.app.model.Employee;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
public class EmployeeRepositoryTests {
	@Autowired
	private EmployeeRepository employeeRepository;

	
	@Test
	@Order(1)
	@Commit //@Commit can be used as direct replacement for @Rollback(false)
	public void saveEmployeeTest() {
		Employee emp = Employee.builder().firstName("sandeep").lastName("Gupta").email("sandy@gmail.com")
				.build();
		employeeRepository.save(emp);
		System.out.println("ID is: "+emp.getId());
		Assertions.assertThat(emp.getId()).isGreaterThan(0);
	}
	
	
	@Test
	@Order(2)
	public void getEmployeeTest() {
		Employee emp = employeeRepository.findById(3L).get();
		System.out.println(emp.getFirstName());
		Assertions.assertThat(emp.getId()).isEqualTo(3L);
	}
	
	@Test
	@Order(3)
	public void getListOfEmployeesTest() {
		List<Employee> empList = employeeRepository.findAll();
		System.out.println(empList.size());
		Assertions.assertThat(empList.size()).isGreaterThan(0);
	}
	
	
	@Test
	@Order(4)
	@Commit
	public void updateEmployeeTest() {
		Employee emp = employeeRepository.findById(14L).get();
		emp.setFirstName("Lakshman");
		emp.setEmail("Lakshman@gmail.com");
		emp.setLastName("Kapa");
		
		Employee empUpdated = employeeRepository.save(emp);
		Assertions.assertThat(empUpdated.getFirstName()).isEqualTo("Lakshman");
	}
	
	
	@Test
	@Order(5)
	public void deleteEmployeeTest() {
//		employeeRepository.deleteById(5L);
		Employee emp = employeeRepository.findById(6L).get();
		
		employeeRepository.delete(emp);
		Employee emp2 = null;
		Optional<Employee> optionalEmployee = employeeRepository.findByFirstName("saiteja");
//		System.out.println(optionalEmployee.toString());
		if(optionalEmployee.isPresent()) {
			emp2 = optionalEmployee.get();
		}
		
		Assertions.assertThat(emp2).isNull();
		
	}
}
