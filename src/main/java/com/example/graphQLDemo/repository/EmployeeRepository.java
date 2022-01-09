package com.example.graphQLDemo.repository;

import com.example.graphQLDemo.domain.Employee;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Integer>,
    JpaSpecificationExecutor<Employee> {
}
