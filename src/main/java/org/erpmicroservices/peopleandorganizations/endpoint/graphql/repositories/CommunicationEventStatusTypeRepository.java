package org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.CommunicationEventStatusType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.UUID;

@GraphQlRepository
public interface CommunicationEventStatusTypeRepository extends JpaRepository<CommunicationEventStatusType, UUID> {

	Page<CommunicationEventStatusType> findCommunicationEventStatusTypeByParentId(final UUID parentId, final Pageable pageable);

	Page<CommunicationEventStatusType> findCommunicationEventStatusTypeByParentIdIsNull(final Pageable pageable);
}
