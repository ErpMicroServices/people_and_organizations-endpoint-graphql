package org.erpmicroservices.peopleandorganizations.backend.repositories;

import org.erpmicroservices.peopleandorganizations.backend.entities.ContactMechanismGeographicBoundary;
import org.erpmicroservices.peopleandorganizations.backend.entities.GeographicBoundary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface ContactMechanismGeographicBoundaryRepository extends JpaRepository<ContactMechanismGeographicBoundary, UUID> {

	@Query(name = "GeographicBoundary.findByContactMechanismId", nativeQuery = true)
	Page<GeographicBoundary> findGeographicBoundaryByContactMechanismId(@Param("contactMechanismId") UUID contactMechanismId, Pageable pageable);
}
