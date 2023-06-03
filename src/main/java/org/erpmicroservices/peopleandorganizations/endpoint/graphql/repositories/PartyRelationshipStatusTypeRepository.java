package org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.relationship.PartyRelationshipStatusType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.UUID;


@GraphQlRepository
public interface PartyRelationshipStatusTypeRepository extends JpaRepository<PartyRelationshipStatusType, UUID> {
	Page<PartyRelationshipStatusType> findPartyRelationshipStatusTypesByParentId(final UUID parentId, final Pageable pageable);

	Page<PartyRelationshipStatusType> findPartyRelationshipStatusTypesByParentIdIsNull(final Pageable pageable);
}
