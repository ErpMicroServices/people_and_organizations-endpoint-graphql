package org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.models.CaseRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.UUID;


@GraphQlRepository
public interface CaseRoleRepository extends JpaRepository<CaseRole, UUID> {

	Page<CaseRole> findByCaseId(final UUID caseId, final Pageable pageable);
}
