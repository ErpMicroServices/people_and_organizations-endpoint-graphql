package org.erpmicroservices.peopleandorganizations.backend.repositories;

import org.erpmicroservices.peopleandorganizations.backend.entities.CommunicationEventType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommunicationEventTypeRepository extends JpaRepository<CommunicationEventType, UUID> {

	Page<CommunicationEventType> findCommunicationEventTypeByParentId(UUID parentId, Pageable pageable);

	Page<CommunicationEventType> findCommunicationEventTypeByParentIdIsNull(final Pageable pageable);
}
