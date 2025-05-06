package org.erpmicroservices.peopleandorganizations.backend.repositories;

import org.erpmicroservices.peopleandorganizations.backend.entities.FacilityTypeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FacilityTypeRepository extends JpaRepository<FacilityTypeEntity, UUID> {

	Page<FacilityTypeEntity> findFacilityTypesByParentId(final UUID parent, final Pageable pageable);

	Page<FacilityTypeEntity> findFacilityTypesByParentIdIsNull(final Pageable pageable);
}
