package org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.relationship.PartyRelationship;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.UUID;


@GraphQlRepository
public interface PartyRelationshipRepository extends PagingAndSortingRepository<PartyRelationship, UUID> {

	Page<PartyRelationship> findPartyRelationshipByFromPartyRoleIdOrToPartyRoleId(UUID fromPartyRoleId, UUID toPartyRoleId, Pageable pageable);
}
