package org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.Party;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.PartyId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.UUID;


@GraphQlRepository
public interface PartyIdRepository extends PagingAndSortingRepository<PartyId, UUID> {

	Page<PartyId> findPartyIdsByPartyEquals(final Party parent, final Pageable pageable);

}
