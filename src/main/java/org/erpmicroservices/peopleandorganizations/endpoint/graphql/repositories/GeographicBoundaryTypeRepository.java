package org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.GeographicBoundaryType;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.UUID;


@GraphQlRepository
public interface GeographicBoundaryTypeRepository extends PagingAndSortingRepository<GeographicBoundaryType, UUID> {

}
