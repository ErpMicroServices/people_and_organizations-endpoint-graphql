package org.erpmicroservices.peopleandorganizations.backend.repositories;

import org.erpmicroservices.peopleandorganizations.backend.entities.Facility;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface FacilityRepository extends JpaRepository<Facility, UUID> {

	Page<Facility> findByPartOfIdIsNull(Pageable pageable);

	Page<Facility> findFacilitiesByPartOfId(UUID facilityId, Pageable pageable);
}
