package com.example.graphQLDemo.resolver;

import com.example.graphQLDemo.domain.Department;
import com.example.graphQLDemo.domain.Employee;
import com.example.graphQLDemo.domain.Organization;
import com.example.graphQLDemo.repository.DepartmentRepository;
import com.example.graphQLDemo.repository.OrganizationRepository;
import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeResolver implements GraphQLResolver<Employee> {

  @Autowired
  private DepartmentRepository departmentRepository;
  @Autowired
  private OrganizationRepository organizationRepository;

  public Organization getOrganization(Employee employee){
    return organizationRepository.findById(employee.getOrganization().getId()).orElseThrow();
  }

  public Department getDepartment(Employee employee){
    return departmentRepository.findById(employee.getDepartment().getId()).orElseThrow();
  }
}
