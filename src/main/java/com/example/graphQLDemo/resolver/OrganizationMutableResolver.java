package com.example.graphQLDemo.resolver;

import com.example.graphQLDemo.domain.Organization;
import com.example.graphQLDemo.domain.OrganizationInput;
import com.example.graphQLDemo.repository.OrganizationRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

@Component
public class OrganizationMutableResolver implements GraphQLMutationResolver {

  OrganizationRepository repository;

  OrganizationMutableResolver(OrganizationRepository repository) {
    this.repository = repository;
  }

  public Organization newOrganization(OrganizationInput organizationInput) {
    return repository.save(new Organization(null, organizationInput.getName(), null, null));
  }

}