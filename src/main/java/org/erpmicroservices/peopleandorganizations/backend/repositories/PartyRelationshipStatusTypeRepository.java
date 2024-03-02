package org.erpmicroservices.peopleandorganizations.backend.repositories;

import org.erpmicroservices.peopleandorganizations.backend.entities.PartyRelationshipStatusType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface PartyRelationshipStatusTypeRepository extends JpaRepository<PartyRelationshipStatusType, UUID> {
	Page<PartyRelationshipStatusType> findPartyRelationshipStatusTypesByParentId(final UUID parentId, final Pageable pageable);

	Page<PartyRelationshipStatusType> findPartyRelationshipStatusTypesByParentIdIsNull(final Pageable pageable);
}
