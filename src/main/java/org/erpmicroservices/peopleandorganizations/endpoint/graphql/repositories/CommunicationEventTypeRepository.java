package org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.CommunicationEventType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.UUID;

@GraphQlRepository
public interface CommunicationEventTypeRepository extends PagingAndSortingRepository<CommunicationEventType, UUID> {

	Page<CommunicationEventType> findCommunicationEventTypeByParentId(UUID parentId, Pageable pageable);

	Page<CommunicationEventType> findCommunicationEventTypeByParentIdIsNull(final Pageable pageable);
}
