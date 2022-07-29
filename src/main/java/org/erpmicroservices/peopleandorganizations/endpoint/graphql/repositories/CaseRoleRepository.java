package org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.CaseRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.UUID;


@GraphQlRepository
public interface CaseRoleRepository extends PagingAndSortingRepository<CaseRole, UUID> {

	Page<CaseRole> findByCaseId(final UUID caseId, final Pageable pageable);
}
