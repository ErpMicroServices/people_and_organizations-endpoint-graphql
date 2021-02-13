package org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.CaseStatusType;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.UUID;

public interface CaseStatusTypeRepo extends PagingAndSortingRepository<CaseStatusType, UUID> {

 List<CaseStatusType> findByDescription(String description);
}
