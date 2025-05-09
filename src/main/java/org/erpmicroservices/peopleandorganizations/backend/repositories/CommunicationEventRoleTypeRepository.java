package org.erpmicroservices.peopleandorganizations.backend.repositories;

import org.erpmicroservices.peopleandorganizations.backend.entities.CommunicationEventRoleTypeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommunicationEventRoleTypeRepository extends JpaRepository<CommunicationEventRoleTypeEntity, UUID> {
	Page<CommunicationEventRoleTypeEntity> findCommunicationEventRoleTypeByParentId(UUID parentId, Pageable pageable);

	Page<CommunicationEventRoleTypeEntity> findCommunicationEventRoleTypeByParentIdIsNull(final Pageable pageable);

	List<CommunicationEventRoleTypeEntity> findCommunicationEventRoleTypeByParentIdIsNotNull();
}
