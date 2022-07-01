package org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.IdType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.UUID;


@GraphQlRepository
public interface IdTypeRepository extends PagingAndSortingRepository<IdType, UUID> {
	Page<IdType> findIdTypesByParentEquals(final IdType parent, final Pageable pageable);

	Page<IdType> findIdTypesByParentIsNull(final Pageable pageable);
}
