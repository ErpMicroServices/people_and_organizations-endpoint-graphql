package org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.CommunicationEventRoleType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.UUID;

@GraphQlRepository
public interface CommunicationEventRoleTypeRepository extends PagingAndSortingRepository<CommunicationEventRoleType, UUID> {
	Page<CommunicationEventRoleType> findCommunicationEventRoleTypeByParentId(UUID parentId, Pageable pageable);

	Page<CommunicationEventRoleType> findCommunicationEventRoleTypeByParentIdIsNull(final Pageable pageable);
}
