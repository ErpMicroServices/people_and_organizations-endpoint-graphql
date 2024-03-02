package org.erpmicroservices.peopleandorganizations.backend.repositories;

import org.erpmicroservices.peopleandorganizations.backend.entities.FacilityType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FacilityTypeRepository extends JpaRepository<FacilityType, UUID> {

	Page<FacilityType> findFacilityTypesByParentId(final UUID parent, final Pageable pageable);

	Page<FacilityType> findFacilityTypesByParentIdIsNull(final Pageable pageable);
}
