package com.example.graphQLDemo.resolver;

import com.example.graphQLDemo.domain.Department;
import com.example.graphQLDemo.domain.DepartmentInput;
import com.example.graphQLDemo.domain.Organization;
import com.example.graphQLDemo.repository.DepartmentRepository;
import com.example.graphQLDemo.repository.OrganizationRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMutableResolver implements GraphQLMutationResolver {

  DepartmentRepository departmentRepository;
  OrganizationRepository organizationRepository;

  DepartmentMutableResolver(DepartmentRepository departmentRepository, OrganizationRepository organizationRepository) {
    this.departmentRepository = departmentRepository;
    this.organizationRepository = organizationRepository;
  }

  public Department newDepartment(DepartmentInput departmentInput) {
    Organization organization = organizationRepository.findById(departmentInput.getOrganizationId()).get();
    return departmentRepository.save(new Department(null, departmentInput.getName(), null, organization));
  }

}
