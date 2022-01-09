package com.example.graphQLDemo.resolver;

import com.example.graphQLDemo.domain.Organization;
import com.example.graphQLDemo.repository.OrganizationRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

@Component
public class OrganizationQueryResolver implements GraphQLQueryResolver {

  private OrganizationRepository repository;

  OrganizationQueryResolver(OrganizationRepository repository) {
    this.repository = repository;
  }

  public Iterable<Organization> organizations() {
    return repository.findAll();
  }

  public Organization organization(Integer id) {
    return repository.findById(id).orElseThrow();
  }
}
