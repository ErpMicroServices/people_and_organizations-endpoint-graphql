package org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.type.PartyType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.UUID;

@GraphQlRepository
public interface PartyTypeRepository extends CrudRepository<PartyType, UUID> {
	Page<PartyType> findPartyTypesByParentId(final UUID parent, final Pageable pageable);

	Page<PartyType> findPartyTypesByParentIdIsNull(final Pageable pageable);
}
