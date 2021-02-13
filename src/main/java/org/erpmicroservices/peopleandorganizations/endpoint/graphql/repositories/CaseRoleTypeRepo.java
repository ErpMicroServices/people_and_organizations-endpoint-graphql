package org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.CaseRoleType;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.UUID;


public interface CaseRoleTypeRepo extends PagingAndSortingRepository<CaseRoleType, UUID> {

 List<CaseRoleType> findByDescription(String description);
}
