package org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.Facility;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.UUID;


@GraphQlRepository
public interface FacilityRepository extends JpaRepository<Facility, UUID> {

	Page<Facility> findByPartOfIsNull(Pageable pageable);

	Page<Facility> findFacilitiesByPartOf(Facility facility, Pageable pageable);
}
