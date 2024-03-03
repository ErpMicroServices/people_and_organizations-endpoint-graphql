package org.erpmicroservices.peopleandorganizations.backend.repositories;

import org.erpmicroservices.peopleandorganizations.backend.entities.FacilityEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface FacilityRepository extends JpaRepository<FacilityEntity, UUID> {

	Page<FacilityEntity> findByPartOfIdIsNull(Pageable pageable);

	Page<FacilityEntity> findFacilitiesByPartOfId(UUID facilityId, Pageable pageable);
}
