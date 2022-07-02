package org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.role.PartyRoleType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.UUID;


@GraphQlRepository
public interface PartyRoleTypeRepository extends PagingAndSortingRepository<PartyRoleType, UUID> {
	Page<PartyRoleType> findPartyRoleTypesByParentEquals(final PartyRoleType parent, final Pageable pageable);

	Page<PartyRoleType> findPartyRoleTypesByParentIsNull(final Pageable pageable);
}
