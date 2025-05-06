package org.erpmicroservices.peopleandorganizations.backend.repositories;

import org.erpmicroservices.peopleandorganizations.backend.entities.FacilityRoleTypeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface FacilityRoleTypeRepository extends JpaRepository<FacilityRoleTypeEntity, UUID> {

	Page<FacilityRoleTypeEntity> findFacilityRoleTypesByParentId(final UUID parent, final Pageable pageable);

	Page<FacilityRoleTypeEntity> findFacilityRoleTypesByParentIdIsNull(final Pageable pageable);
}
