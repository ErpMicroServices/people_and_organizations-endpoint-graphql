package org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.model.CommunicationEventPurpose;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.UUID;


@GraphQlRepository
public interface CommunicationEventPurposeRepository extends JpaRepository<CommunicationEventPurpose, UUID> {

}
