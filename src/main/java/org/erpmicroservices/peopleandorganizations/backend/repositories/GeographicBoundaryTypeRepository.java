package org.erpmicroservices.peopleandorganizations.backend.repositories;

import org.erpmicroservices.peopleandorganizations.backend.entities.GeographicBoundaryTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface GeographicBoundaryTypeRepository extends JpaRepository<GeographicBoundaryTypeEntity, UUID> {

}
