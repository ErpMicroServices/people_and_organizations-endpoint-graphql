package org.erpmicroservices.peopleandorganizations.backend.repositories;

import org.erpmicroservices.peopleandorganizations.backend.entities.PartyContactMechanism;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface PartyContactMechanismRepository extends JpaRepository<PartyContactMechanism, UUID> {

	Page<PartyContactMechanism> findPartyContactMechanismByPartyId(UUID partyId, Pageable pageable);
}