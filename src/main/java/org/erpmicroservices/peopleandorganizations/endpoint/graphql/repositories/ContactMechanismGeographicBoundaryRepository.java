package org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.ContactMechanismGeographicBoundary;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.GeographicBoundary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.UUID;


@GraphQlRepository
public interface ContactMechanismGeographicBoundaryRepository extends PagingAndSortingRepository<ContactMechanismGeographicBoundary, UUID> {

	Page<GeographicBoundary> findContactMechanismGeographicBoundariesByContactMechanism_Id(UUID contactMechanismId, Pageable pageable);
}
