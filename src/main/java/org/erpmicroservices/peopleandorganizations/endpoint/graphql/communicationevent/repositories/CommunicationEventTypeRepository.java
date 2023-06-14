package org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.model.CommunicationEventType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.UUID;

@GraphQlRepository
public interface CommunicationEventTypeRepository extends JpaRepository<CommunicationEventType, UUID> {

	Page<CommunicationEventType> findCommunicationEventTypeByParentId(UUID parentId, Pageable pageable);

	Page<CommunicationEventType> findCommunicationEventTypeByParentIdIsNull(final Pageable pageable);
}
