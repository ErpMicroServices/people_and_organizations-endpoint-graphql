package org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.PartyRelationshipType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.UUID;


@GraphQlRepository
public interface PartyRelationshipTypeRepository extends PagingAndSortingRepository<PartyRelationshipType, UUID> {
	Page<PartyRelationshipType> findPartyRelationshipTypesByParentEquals(final PartyRelationshipType parent, final Pageable pageable);

	Page<PartyRelationshipType> findPartyRelationshipTypesByParentIsNull(final Pageable pageable);
}
