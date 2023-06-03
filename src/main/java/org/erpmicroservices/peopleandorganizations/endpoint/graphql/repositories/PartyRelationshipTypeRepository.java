package org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.relationship.PartyRelationshipType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.UUID;


@GraphQlRepository
public interface PartyRelationshipTypeRepository extends JpaRepository<PartyRelationshipType, UUID> {
	Page<PartyRelationshipType> findPartyRelationshipTypesByParentId(final UUID parentId, final Pageable pageable);

	Page<PartyRelationshipType> findPartyRelationshipTypesByParentIdIsNull(final Pageable pageable);
}
