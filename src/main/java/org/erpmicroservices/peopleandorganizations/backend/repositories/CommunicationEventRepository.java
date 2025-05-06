package org.erpmicroservices.peopleandorganizations.backend.repositories;

import org.erpmicroservices.peopleandorganizations.backend.entities.CommunicationEventEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface CommunicationEventRepository extends JpaRepository<CommunicationEventEntity, UUID> {

	List<CommunicationEventEntity> findByCaseId(UUID caseId, final Pageable pageable);
}
