package org.erpmicroservices.peopleandorganizations.backend.repositories;

import org.erpmicroservices.peopleandorganizations.backend.entities.CommunicationEventStatusTypeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommunicationEventStatusTypeRepository extends JpaRepository<CommunicationEventStatusTypeEntity, UUID> {

	Page<CommunicationEventStatusTypeEntity> findCommunicationEventStatusTypeByParentId(final UUID parentId, final Pageable pageable);

	Page<CommunicationEventStatusTypeEntity> findCommunicationEventStatusTypeByParentIdIsNull(final Pageable pageable);
}
