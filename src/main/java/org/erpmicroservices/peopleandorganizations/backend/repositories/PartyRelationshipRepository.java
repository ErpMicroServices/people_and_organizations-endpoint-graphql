package org.erpmicroservices.peopleandorganizations.backend.repositories;

import org.erpmicroservices.peopleandorganizations.backend.entities.PartyRelationshipEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface PartyRelationshipRepository extends JpaRepository<PartyRelationshipEntity, UUID> {

	Page<PartyRelationshipEntity> findPartyRelationshipByFromPartyRoleIdOrToPartyRoleId(UUID fromPartyRoleId, UUID toPartyRoleId, Pageable pageable);
}
