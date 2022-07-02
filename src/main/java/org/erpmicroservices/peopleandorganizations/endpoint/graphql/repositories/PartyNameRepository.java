package org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.Party;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.name.PartyName;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.UUID;


@GraphQlRepository
public interface PartyNameRepository extends PagingAndSortingRepository<PartyName, UUID> {

	Page<PartyName> findPartyNamesByParty(final Party parent, final Pageable pageable);

}
