package com.example.graphQLDemo.repository;

import com.example.graphQLDemo.domain.Organization;
import org.springframework.data.repository.CrudRepository;

public interface OrganizationRepository extends CrudRepository<Organization, Integer> {
}
