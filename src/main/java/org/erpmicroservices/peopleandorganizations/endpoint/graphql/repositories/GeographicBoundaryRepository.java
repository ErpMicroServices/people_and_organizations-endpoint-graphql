package org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.GeographicBoundary;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.UUID;


@GraphQlRepository
public interface GeographicBoundaryRepository extends PagingAndSortingRepository<GeographicBoundary, UUID> {

}
