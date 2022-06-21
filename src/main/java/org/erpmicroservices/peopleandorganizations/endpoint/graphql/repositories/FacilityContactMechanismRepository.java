package org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.Facility;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.FacilityContactMechanism;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.List;
import java.util.UUID;


@GraphQlRepository
public interface FacilityContactMechanismRepository extends PagingAndSortingRepository<FacilityContactMechanism, UUID> {

	List<FacilityContactMechanism> findByFacility(Facility facility);
}
