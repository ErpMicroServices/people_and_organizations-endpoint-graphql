package org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.FacilityType;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.UUID;

@GraphQlRepository
public interface FacilityTypeRepository extends PagingAndSortingRepository<FacilityType, UUID> {

}
