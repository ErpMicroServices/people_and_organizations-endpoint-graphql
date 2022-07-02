package org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.facility.FacilityRoleType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.UUID;


@GraphQlRepository
public interface FacilityRoleTypeRepository extends PagingAndSortingRepository<FacilityRoleType, UUID> {

	Page<FacilityRoleType> findFacilityRoleTypesByParentEquals(final FacilityRoleType parent, final Pageable pageable);

	Page<FacilityRoleType> findFacilityRoleTypesByParentIsNull(final Pageable pageable);
}
