package org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.facility.FacilityRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.UUID;


@GraphQlRepository
public interface FacilityRoleRepository extends JpaRepository<FacilityRole, UUID> {

	Page<FacilityRole> findByFacilityId(UUID parentId, Pageable pageable);
}
