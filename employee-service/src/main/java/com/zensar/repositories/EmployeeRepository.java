package com.zensar.repositories;

import com.zensar.entity.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

	public long countByEmail(String email);

	@Query("select e.email from Employee e where e.employeename = ?1")
	String findEmailByEmployeename(String username);
}