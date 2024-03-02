package org.erpmicroservices.peopleandorganizations.backend.repositories;

import org.erpmicroservices.peopleandorganizations.backend.entities.PartyRoleType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface PartyRoleTypeRepository extends JpaRepository<PartyRoleType, UUID> {
	Page<PartyRoleType> findPartyRoleTypesByParentId(final UUID parentId, final Pageable pageable);

	Page<PartyRoleType> findPartyRoleTypesByParentIdIsNull(final Pageable pageable);
}
