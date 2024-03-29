package org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.role.PartyRoleType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.UUID;


@GraphQlRepository
public interface PartyRoleTypeRepository extends JpaRepository<PartyRoleType, UUID> {
	Page<PartyRoleType> findPartyRoleTypesByParentId(final UUID parentId, final Pageable pageable);

	Page<PartyRoleType> findPartyRoleTypesByParentIdIsNull(final Pageable pageable);
}
