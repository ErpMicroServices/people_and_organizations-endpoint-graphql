package org.erpmicroservices.peopleandorganizations.backend.repositories;

import org.erpmicroservices.peopleandorganizations.backend.entities.FacilityContactMechanism;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface FacilityContactMechanismRepository extends JpaRepository<FacilityContactMechanism, UUID> {

	Page<FacilityContactMechanism> findByFacilityId(UUID id, Pageable pageable);
}
