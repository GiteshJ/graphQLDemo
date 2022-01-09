package com.example.graphQLDemo.resolver;

import com.example.graphQLDemo.domain.Department;
import com.example.graphQLDemo.domain.Employee;
import com.example.graphQLDemo.domain.EmployeeInput;
import com.example.graphQLDemo.domain.Organization;
import com.example.graphQLDemo.repository.DepartmentRepository;
import com.example.graphQLDemo.repository.EmployeeRepository;
import com.example.graphQLDemo.repository.OrganizationRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMutableResolver implements GraphQLMutationResolver {

  DepartmentRepository departmentRepository;
  EmployeeRepository employeeRepository;
  OrganizationRepository organizationRepository;

  EmployeeMutableResolver(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository, OrganizationRepository organizationRepository) {
    this.departmentRepository = departmentRepository;
    this.employeeRepository = employeeRepository;
    this.organizationRepository = organizationRepository;
  }

  public Employee newEmployee(EmployeeInput employeeInput) {
    Department department = departmentRepository.findById(employeeInput.getDepartmentId()).get();
    Organization organization = organizationRepository.findById(employeeInput.getOrganizationId()).get();
    return employeeRepository.save(new Employee(null, employeeInput.getFirstName(), employeeInput.getLastName(),
        employeeInput.getPosition(), employeeInput.getSalary(), employeeInput.getAge(),
        department, organization));
  }

}
