package org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.CaseStatusType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.CaseType;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.UUID;

public interface CaseTypeRepo extends PagingAndSortingRepository<CaseStatusType, UUID> {

 List<CaseType> findByDescription(String description);
}
