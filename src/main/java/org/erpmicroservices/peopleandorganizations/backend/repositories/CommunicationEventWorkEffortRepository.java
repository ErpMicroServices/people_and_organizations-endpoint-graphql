package org.erpmicroservices.peopleandorganizations.backend.repositories;

import org.erpmicroservices.peopleandorganizations.backend.entities.CommunicationEventWorkEffortEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommunicationEventWorkEffortRepository extends JpaRepository<CommunicationEventWorkEffortEntity, UUID> {
    Page<CommunicationEventWorkEffortEntity> findByCommunicationEventId(UUID communicationEventId, Pageable pageable);

    Page<CommunicationEventWorkEffortEntity> findByWorkEffortId(UUID workEffortId, Pageable pageable);
}
