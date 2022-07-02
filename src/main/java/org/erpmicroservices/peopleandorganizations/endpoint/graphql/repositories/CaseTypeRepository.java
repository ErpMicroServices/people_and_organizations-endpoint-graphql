package org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.CaseType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface CaseTypeRepository extends PagingAndSortingRepository<CaseType, UUID> {
	Page<CaseType> findCaseTypeByParentEquals(CaseType parent, Pageable pageable);

	Page<CaseType> findCaseTypeByParentIsNull(final Pageable pageable);
}
