package org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.contactmechanism.ContactMechanismGeographicBoundary;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.geographicboundary.GeographicBoundary;
import org.hibernate.annotations.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.UUID;


@GraphQlRepository
public interface ContactMechanismGeographicBoundaryRepository extends PagingAndSortingRepository<ContactMechanismGeographicBoundary, UUID> {


	@Type(type = "uuid-char")
	@Query(value = "select cast(geographic_boundary.id as varchar) as id, geographic_boundary.geo_code, geographic_boundary.name,\n" +
			               "       geographic_boundary.abbreviation, cast(geographic_boundary.geographic_boundary_type_id as varchar) geographic_boundary_type_id\n" +
			               "from geographic_boundary, contact_mechanism_geographic_boundary\n" +
			               "where\n" +
			               "    contact_mechanism_geographic_boundary.contact_mechanism_id = :contactMechanismId\n" +
			               "and\n" +
			               "    geographic_boundary.id = contact_mechanism_geographic_boundary.geographic_boundary_id\n",
			countQuery = "select count(*) " +
					             "from geographic_boundary, contact_mechanism_geographic_boundary\n" +
					             "where\n" +
					             "    contact_mechanism_geographic_boundary.contact_mechanism_id = :contactMechanismId\n" +
					             "and\n" +
					             "    geographic_boundary.id = contact_mechanism_geographic_boundary.geographic_boundary_id\n",
			nativeQuery = true)
	Page<GeographicBoundary> findByContactMechanismId(@Param("contactMechanismId") UUID contactMechanismId, Pageable pageable);
}
