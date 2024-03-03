package org.erpmicroservices.peopleandorganizations.backend.repositories;

import org.erpmicroservices.peopleandorganizations.backend.entities.CommunicationEventTypeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommunicationEventTypeRepository extends JpaRepository<CommunicationEventTypeEntity, UUID> {

	Page<CommunicationEventTypeEntity> findCommunicationEventTypeByParentId(UUID parentId, Pageable pageable);

	Page<CommunicationEventTypeEntity> findCommunicationEventTypeByParentIdIsNull(final Pageable pageable);
}
