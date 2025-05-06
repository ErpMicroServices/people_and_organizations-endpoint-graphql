package org.erpmicroservices.peopleandorganizations.backend.repositories;

import org.erpmicroservices.peopleandorganizations.backend.entities.GeographicBoundaryAssociationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GeographicBoundaryAssociationRepository extends JpaRepository<GeographicBoundaryAssociationEntity, UUID> {
    Page<GeographicBoundaryAssociationEntity> findByWithinBoundary(UUID withinBoundary, Pageable pageable);

    Page<GeographicBoundaryAssociationEntity> findByInBoundary(UUID inBoundary, Pageable pageable);
}
