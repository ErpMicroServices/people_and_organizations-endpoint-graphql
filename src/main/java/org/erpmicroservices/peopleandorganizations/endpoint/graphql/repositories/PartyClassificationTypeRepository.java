package org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.classification.PartyClassificationType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.UUID;


@GraphQlRepository
public interface PartyClassificationTypeRepository extends PagingAndSortingRepository<PartyClassificationType, UUID> {

	Page<PartyClassificationType> findPartyClassificationTypesByParentEquals(final PartyClassificationType parent, final Pageable pageable);

	Page<PartyClassificationType> findPartyClassificationTypesByParentIsNull(final Pageable pageable);
}
