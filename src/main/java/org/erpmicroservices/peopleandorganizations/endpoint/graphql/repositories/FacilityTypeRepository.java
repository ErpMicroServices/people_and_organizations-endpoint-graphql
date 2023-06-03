package org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.facility.FacilityType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.UUID;

@GraphQlRepository
public interface FacilityTypeRepository extends JpaRepository<FacilityType, UUID> {

	Page<FacilityType> findFacilityTypesByParentId(final UUID parent, final Pageable pageable);

	Page<FacilityType> findFacilityTypesByParentIdIsNull(final Pageable pageable);
}
