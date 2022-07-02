package org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.Party;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.role.PartyRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.UUID;


@GraphQlRepository
public interface PartyRoleRepository extends PagingAndSortingRepository<PartyRole, UUID> {
	Page<PartyRole> findPartyRolesByParty(final Party party, final Pageable pageable);

}
