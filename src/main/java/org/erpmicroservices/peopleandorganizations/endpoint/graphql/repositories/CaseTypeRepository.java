package org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.CaseType;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CaseTypeRepository extends CrudRepository<CaseType, UUID> {
}
