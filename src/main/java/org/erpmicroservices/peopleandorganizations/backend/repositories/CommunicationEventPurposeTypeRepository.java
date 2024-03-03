package org.erpmicroservices.peopleandorganizations.backend.repositories;

import org.erpmicroservices.peopleandorganizations.backend.entities.CommunicationEventPurposeTypeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommunicationEventPurposeTypeRepository extends JpaRepository<CommunicationEventPurposeTypeEntity, UUID> {

	Page<CommunicationEventPurposeTypeEntity> findCommunicationEventPurposeTypeByParentId(UUID parentId, Pageable pageable);

	Page<CommunicationEventPurposeTypeEntity> findCommunicationEventPurposeTypeByParentIdIsNull(final Pageable pageable);

	List<CommunicationEventPurposeTypeEntity> findCommunicationEventPurposeTypeByParentIdIsNotNull();
}
