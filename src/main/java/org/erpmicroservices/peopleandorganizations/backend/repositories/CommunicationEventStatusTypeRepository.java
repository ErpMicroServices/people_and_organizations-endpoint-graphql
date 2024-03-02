package org.erpmicroservices.peopleandorganizations.backend.repositories;

import org.erpmicroservices.peopleandorganizations.backend.entities.CommunicationEventStatusType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommunicationEventStatusTypeRepository extends JpaRepository<CommunicationEventStatusType, UUID> {

	Page<CommunicationEventStatusType> findCommunicationEventStatusTypeByParentId(final UUID parentId, final Pageable pageable);

	Page<CommunicationEventStatusType> findCommunicationEventStatusTypeByParentIdIsNull(final Pageable pageable);
}
