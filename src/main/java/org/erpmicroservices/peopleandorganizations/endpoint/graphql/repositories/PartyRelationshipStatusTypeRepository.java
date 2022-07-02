package org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.PartyRelationshipStatusType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.UUID;


@GraphQlRepository
public interface PartyRelationshipStatusTypeRepository extends PagingAndSortingRepository<PartyRelationshipStatusType, UUID> {
	Page<PartyRelationshipStatusType> findPartyRelationshipStatusTypesByParentEquals(final PartyRelationshipStatusType parent, final Pageable pageable);

	Page<PartyRelationshipStatusType> findPartyRelationshipStatusTypesByParentIsNull(final Pageable pageable);
}
