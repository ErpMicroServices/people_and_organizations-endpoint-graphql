package org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.contactmechanism.PartyContactMechanismPurposeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.UUID;


@GraphQlRepository
public interface PartyContactMechanismPurposeTypeRepository extends PagingAndSortingRepository<PartyContactMechanismPurposeType, UUID> {
	Page<PartyContactMechanismPurposeType> findPartyContactMechanismPurposeTypesByParentEquals(final PartyContactMechanismPurposeType parent, final Pageable pageable);

	Page<PartyContactMechanismPurposeType> findPartyContactMechanismPurposeTypesByParentIsNull(final Pageable pageable);
}
