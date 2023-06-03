package org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.CommunicationEventRoleType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.List;
import java.util.UUID;

@GraphQlRepository
public interface CommunicationEventRoleTypeRepository extends JpaRepository<CommunicationEventRoleType, UUID> {
	Page<CommunicationEventRoleType> findCommunicationEventRoleTypeByParentId(UUID parentId, Pageable pageable);

	Page<CommunicationEventRoleType> findCommunicationEventRoleTypeByParentIdIsNull(final Pageable pageable);

	List<CommunicationEventRoleType> findCommunicationEventRoleTypeByParentIdIsNotNull();
}
