package org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.PartyType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.UUID;

@GraphQlRepository
public interface PartyTypeRepository extends CrudRepository<PartyType, UUID> {
	Page<PartyType> findPartyTypesByParentEquals(final PartyType parent, final Pageable pageable);

	Page<PartyType> findPartyTypesByParentIsNull(final Pageable pageable);
}
