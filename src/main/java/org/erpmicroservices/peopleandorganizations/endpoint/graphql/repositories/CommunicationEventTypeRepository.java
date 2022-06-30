package org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.CommunicationEventType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.UUID;

@GraphQlRepository
public interface CommunicationEventTypeRepository extends PagingAndSortingRepository<CommunicationEventType, UUID> {

	Page<CommunicationEventType> findCommunicationEventTypeByParentEquals(CommunicationEventType parent, Pageable pageable);

	Page<CommunicationEventType> findCommunicationEventTypeByParentIsNull(final Pageable pageable);
}
