package org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.Case;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface CaseRepo extends PagingAndSortingRepository<Case, UUID> {
}
