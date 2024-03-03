package org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.models;

import org.erpmicroservices.peopleandorganizations.backend.entities.CaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface CaseRepository extends JpaRepository<CaseEntity, UUID> {

    Page<CaseEntity> findByCaseTypeId(final UUID caseTypeId, final Pageable pageable);
    Page<CaseEntity> findByCaseStatusTypeId(final UUID caseTypeId, final Pageable pageable);
}
