package org.erpmicroservices.peopleandorganizations.backend.repositories;

import org.erpmicroservices.peopleandorganizations.backend.entities.PartyRelationshipType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface PartyRelationshipTypeRepository extends JpaRepository<PartyRelationshipType, UUID> {
	Page<PartyRelationshipType> findPartyRelationshipTypesByParentId(final UUID parentId, final Pageable pageable);

	Page<PartyRelationshipType> findPartyRelationshipTypesByParentIdIsNull(final Pageable pageable);
}
