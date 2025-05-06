package org.erpmicroservices.peopleandorganizations.backend.repositories;

import org.erpmicroservices.peopleandorganizations.backend.entities.PartyRelationshipTypeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface PartyRelationshipTypeRepository extends JpaRepository<PartyRelationshipTypeEntity, UUID> {
	Page<PartyRelationshipTypeEntity> findPartyRelationshipTypesByParentId(final UUID parentId, final Pageable pageable);

	Page<PartyRelationshipTypeEntity> findPartyRelationshipTypesByParentIdIsNull(final Pageable pageable);
}
