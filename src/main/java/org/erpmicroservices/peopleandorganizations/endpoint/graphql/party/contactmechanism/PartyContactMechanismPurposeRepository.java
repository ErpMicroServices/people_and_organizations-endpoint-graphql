package org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.contactmechanism;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.UUID;


@GraphQlRepository
public interface PartyContactMechanismPurposeRepository extends JpaRepository<PartyContactMechanismPurpose, UUID> {

}
