package org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.models.Case;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.List;
import java.util.UUID;


@GraphQlRepository
public interface CaseRepository extends JpaRepository<Case, UUID> {

    Page<Case> findByCaseTypeId(final UUID caseTypeId, final Pageable pageable);
}
