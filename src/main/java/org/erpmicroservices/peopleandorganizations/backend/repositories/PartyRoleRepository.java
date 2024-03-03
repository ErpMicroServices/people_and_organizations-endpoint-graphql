package org.erpmicroservices.peopleandorganizations.backend.repositories;

import org.erpmicroservices.peopleandorganizations.backend.entities.PartyRoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface PartyRoleRepository extends JpaRepository<PartyRoleEntity, UUID> {
	Page<PartyRoleEntity> findPartyRolesByPartyId(final UUID partyId, final Pageable pageable);

}
