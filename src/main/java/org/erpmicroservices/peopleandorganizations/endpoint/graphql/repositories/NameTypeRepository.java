package org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.name.NameType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.UUID;


@GraphQlRepository
public interface NameTypeRepository extends PagingAndSortingRepository<NameType, UUID> {
	Page<NameType> findNameTypesByParentId(final UUID parentId, final Pageable pageable);

	Page<NameType> findNameTypesByParentIdIsNull(final Pageable pageable);
}
