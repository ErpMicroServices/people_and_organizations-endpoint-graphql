package org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.contactmechanism;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.UUID;


@GraphQlRepository
public interface PartyContactMechanismPurposeRepository extends PagingAndSortingRepository<PartyContactMechanismPurpose, UUID> {

}
