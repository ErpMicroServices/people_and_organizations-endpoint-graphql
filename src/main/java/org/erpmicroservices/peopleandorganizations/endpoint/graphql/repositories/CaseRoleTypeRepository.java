package org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.CaseRoleType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.UUID;

@GraphQlRepository
public interface CaseRoleTypeRepository extends PagingAndSortingRepository<CaseRoleType, UUID> {

	Page<CaseRoleType> findCaseRoleTypesByParentEquals(final CaseRoleType parent, final Pageable pageable);

	Page<CaseRoleType> findCaseRoleTypesByParentIsNull(final Pageable pageable);
}
