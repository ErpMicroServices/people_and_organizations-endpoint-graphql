package org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.CaseStatusType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.UUID;

@GraphQlRepository
public interface CaseStatusTypeRepository extends PagingAndSortingRepository<CaseStatusType, UUID> {
	Page<CaseStatusType> findCaseStatusTypeByParentId(UUID parentId, Pageable pageable);

	Page<CaseStatusType> findCaseStatusTypeByParentIdIsNull(final Pageable pageable);
}
