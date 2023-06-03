package org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.classification.PartyClassification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.UUID;


@GraphQlRepository
public interface PartyClassificationRepository extends JpaRepository<PartyClassification, UUID> {

	Page<PartyClassification> findPartyClassificationsByPartyId(UUID partyId, Pageable pageable);
}
