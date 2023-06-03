package org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.geographicboundary.GeographicBoundaryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.UUID;


@GraphQlRepository
public interface GeographicBoundaryTypeRepository extends JpaRepository<GeographicBoundaryType, UUID> {

}
