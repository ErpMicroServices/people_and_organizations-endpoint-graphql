package org.erpmicroservices.peopleandorganizations.backend.repositories;

import org.erpmicroservices.peopleandorganizations.backend.entities.PartyContactMechanismEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface PartyContactMechanismRepository extends JpaRepository<PartyContactMechanismEntity, UUID> {

	Page<PartyContactMechanismEntity> findPartyContactMechanismByPartyId(UUID partyId, Pageable pageable);
}
