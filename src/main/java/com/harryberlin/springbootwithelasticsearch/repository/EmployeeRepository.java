package com.harryberlin.springbootwithelasticsearch.repository;

import com.harryberlin.springbootwithelasticsearch.domain.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
}
