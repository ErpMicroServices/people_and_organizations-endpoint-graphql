package org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.facility.FacilityContactMechanism;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.UUID;


@GraphQlRepository
public interface FacilityContactMechanismRepository extends PagingAndSortingRepository<FacilityContactMechanism, UUID> {

	Page<FacilityContactMechanism> findByFacility_Id(UUID id, Pageable pageable);
}
