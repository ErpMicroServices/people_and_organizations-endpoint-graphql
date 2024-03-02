package org.erpmicroservices.peopleandorganizations.backend.repositories;

import org.erpmicroservices.peopleandorganizations.backend.entities.PartyClassification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface PartyClassificationRepository extends JpaRepository<PartyClassification, UUID> {

	Page<PartyClassification> findPartyClassificationsByPartyId(UUID partyId, Pageable pageable);
}
