package org.erpmicroservices.peopleandorganizations.backend.repositories;

import org.erpmicroservices.peopleandorganizations.backend.entities.PartyRelationshipStatusTypeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface PartyRelationshipStatusTypeRepository extends JpaRepository<PartyRelationshipStatusTypeEntity, UUID> {
	Page<PartyRelationshipStatusTypeEntity> findPartyRelationshipStatusTypesByParentId(final UUID parentId, final Pageable pageable);

	Page<PartyRelationshipStatusTypeEntity> findPartyRelationshipStatusTypesByParentIdIsNull(final Pageable pageable);
}
