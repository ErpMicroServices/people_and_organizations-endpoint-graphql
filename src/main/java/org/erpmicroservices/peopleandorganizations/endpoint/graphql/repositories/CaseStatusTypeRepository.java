package org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.CaseStatusType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.UUID;

@GraphQlRepository
public interface CaseStatusTypeRepository extends CrudRepository<CaseStatusType, UUID> {
}
