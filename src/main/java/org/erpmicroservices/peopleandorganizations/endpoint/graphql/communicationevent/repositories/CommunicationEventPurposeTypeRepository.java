package org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.model.CommunicationEventPurposeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.List;
import java.util.UUID;

@GraphQlRepository
public interface CommunicationEventPurposeTypeRepository extends JpaRepository<CommunicationEventPurposeType, UUID> {

	Page<CommunicationEventPurposeType> findCommunicationEventPurposeTypeByParentId(UUID parentId, Pageable pageable);

	Page<CommunicationEventPurposeType> findCommunicationEventPurposeTypeByParentIdIsNull(final Pageable pageable);

	List<CommunicationEventPurposeType> findCommunicationEventPurposeTypeByParentIdIsNotNull();
}
