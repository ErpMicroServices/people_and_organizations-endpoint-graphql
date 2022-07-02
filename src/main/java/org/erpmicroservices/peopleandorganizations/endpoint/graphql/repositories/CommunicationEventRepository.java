package org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.CommunicationEvent;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.List;
import java.util.UUID;


@GraphQlRepository
public interface CommunicationEventRepository extends PagingAndSortingRepository<CommunicationEvent, UUID> {

	List<CommunicationEvent> findByKase_Id(UUID caseId, final Pageable pageable);
}
