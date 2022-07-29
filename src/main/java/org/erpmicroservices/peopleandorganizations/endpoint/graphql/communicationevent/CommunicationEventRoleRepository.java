package org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.UUID;


@GraphQlRepository
public interface CommunicationEventRoleRepository extends PagingAndSortingRepository<CommunicationEventRole, UUID> {
	Page<CommunicationEventRole> findByCommunicationEventId(final UUID communicationEventId, final Pageable pageable);
}
