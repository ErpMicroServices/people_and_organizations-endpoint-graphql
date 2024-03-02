package org.erpmicroservices.peopleandorganizations.backend.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.model.CommunicationEvent;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface CommunicationEventRepository extends JpaRepository<CommunicationEvent, UUID> {

	List<CommunicationEvent> findByCaseId(UUID caseId, final Pageable pageable);
}
