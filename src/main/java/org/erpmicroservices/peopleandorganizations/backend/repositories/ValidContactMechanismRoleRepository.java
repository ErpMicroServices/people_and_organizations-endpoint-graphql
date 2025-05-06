package org.erpmicroservices.peopleandorganizations.backend.repositories;

import org.erpmicroservices.peopleandorganizations.backend.entities.ValidContactMechanismRoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ValidContactMechanismRoleRepository extends JpaRepository<ValidContactMechanismRoleEntity, UUID> {
    Page<ValidContactMechanismRoleEntity> findByContactMechanismTypeId(UUID contactMechanismTypeId, Pageable pageable);

    Page<ValidContactMechanismRoleEntity> findByCommunicationEventRoleTypeId(UUID communicationEventRoleTypeId, Pageable pageable);
}
