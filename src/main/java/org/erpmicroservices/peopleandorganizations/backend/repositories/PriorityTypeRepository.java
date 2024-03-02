package org.erpmicroservices.peopleandorganizations.backend.repositories;

import org.erpmicroservices.peopleandorganizations.backend.entities.PriorityType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface PriorityTypeRepository extends JpaRepository<PriorityType, UUID> {
	Page<PriorityType> findPartyRelationshipPriorityTypesByParentId(final UUID parentId, final Pageable pageable);

	Page<PriorityType> findPartyRelationshipPriorityTypesByParentIdIsNull(final Pageable pageable);
}
