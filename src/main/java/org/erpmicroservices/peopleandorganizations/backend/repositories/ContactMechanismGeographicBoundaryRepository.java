package org.erpmicroservices.peopleandorganizations.backend.repositories;

import org.erpmicroservices.peopleandorganizations.backend.entities.ContactMechanismGeographicBoundaryEntity;
import org.erpmicroservices.peopleandorganizations.backend.entities.GeographicBoundaryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface ContactMechanismGeographicBoundaryRepository extends JpaRepository<ContactMechanismGeographicBoundaryEntity, UUID> {

	@Query(name = "GeographicBoundaryEntity.findByContactMechanismId", nativeQuery = true)
	Page<GeographicBoundaryEntity> findGeographicBoundaryByContactMechanismId(@Param("contactMechanismId") UUID contactMechanismId, Pageable pageable);
}
