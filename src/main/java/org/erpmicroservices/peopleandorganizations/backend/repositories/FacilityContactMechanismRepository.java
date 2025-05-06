package org.erpmicroservices.peopleandorganizations.backend.repositories;

import org.erpmicroservices.peopleandorganizations.backend.entities.FacilityContactMechanismEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface FacilityContactMechanismRepository extends JpaRepository<FacilityContactMechanismEntity, UUID> {

	Page<FacilityContactMechanismEntity> findByFacilityId(UUID id, Pageable pageable);
}
