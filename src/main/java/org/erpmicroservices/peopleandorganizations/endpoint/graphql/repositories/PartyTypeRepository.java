package org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.type.PartyType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PartyTypeRepository extends JpaRepository<PartyType, UUID> {
	Page<PartyType> findPartyTypesByParentId(final UUID parent, final Pageable pageable);

	Page<PartyType> findPartyTypesByParentIdIsNull(final Pageable pageable);

	List<PartyType> findPartyTypesByParentIdIsNotNull();
}
