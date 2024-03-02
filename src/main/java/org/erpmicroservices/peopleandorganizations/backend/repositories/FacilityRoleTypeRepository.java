package org.erpmicroservices.peopleandorganizations.backend.repositories;

import org.erpmicroservices.peopleandorganizations.backend.entities.FacilityRoleType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface FacilityRoleTypeRepository extends JpaRepository<FacilityRoleType, UUID> {

	Page<FacilityRoleType> findFacilityRoleTypesByParentId(final UUID parent, final Pageable pageable);

	Page<FacilityRoleType> findFacilityRoleTypesByParentIdIsNull(final Pageable pageable);
}
