package org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.PriorityType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.UUID;


@GraphQlRepository
public interface PartyRelationshipPriorityTypeRepository extends PagingAndSortingRepository<PriorityType, UUID> {
	Page<PriorityType> findPartyRelationshipPriorityTypesByParentId(final UUID parentId, final Pageable pageable);

	Page<PriorityType> findPartyRelationshipPriorityTypesByParentIdIsNull(final Pageable pageable);
}
