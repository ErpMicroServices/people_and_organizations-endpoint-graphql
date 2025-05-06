package org.erpmicroservices.peopleandorganizations.backend.repositories;

import org.erpmicroservices.peopleandorganizations.backend.entities.PriorityTypeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface PriorityTypeRepository extends JpaRepository<PriorityTypeEntity, UUID> {
	Page<PriorityTypeEntity> findPartyRelationshipPriorityTypesByParentId(final UUID parentId, final Pageable pageable);

	Page<PriorityTypeEntity> findPartyRelationshipPriorityTypesByParentIdIsNull(final Pageable pageable);
}
