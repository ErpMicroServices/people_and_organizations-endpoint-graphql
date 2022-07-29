package org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.CommunicationEventPurposeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.UUID;

@GraphQlRepository
public interface CommunicationEventPurposeTypeRepository extends PagingAndSortingRepository<CommunicationEventPurposeType, UUID> {

	Page<CommunicationEventPurposeType> findCommunicationEventPurposeTypeByParentId(UUID parentId, Pageable pageable);

	Page<CommunicationEventPurposeType> findCommunicationEventPurposeTypeByParentIdIsNull(final Pageable pageable);
}
