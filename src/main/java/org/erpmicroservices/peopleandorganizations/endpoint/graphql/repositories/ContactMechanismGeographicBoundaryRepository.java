package org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.contactmechanism.ContactMechanismGeographicBoundary;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.geographicboundary.GeographicBoundary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.UUID;


@GraphQlRepository
public interface ContactMechanismGeographicBoundaryRepository extends PagingAndSortingRepository<ContactMechanismGeographicBoundary, UUID> {

	@Query(name = "GeographicBoundary.findByContactMechanismId", nativeQuery = true)
	Page<GeographicBoundary> findGeographicBoundaryByContactMechanismId(@Param("contactMechanismId") UUID contactMechanismId, Pageable pageable);
}
