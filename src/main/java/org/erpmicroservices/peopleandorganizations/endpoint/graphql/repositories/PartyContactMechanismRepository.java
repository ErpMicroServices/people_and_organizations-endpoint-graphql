package org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.contactmechanism.PartyContactMechanism;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.UUID;


@GraphQlRepository
public interface PartyContactMechanismRepository extends JpaRepository<PartyContactMechanism, UUID> {

	Page<PartyContactMechanism> findPartyContactMechanismByPartyId(UUID partyId, Pageable pageable);
}
