package org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.models.CaseType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.List;
import java.util.UUID;

@GraphQlRepository
public interface CaseTypeRepository extends JpaRepository<CaseType, UUID> {
    Page<CaseType> findCaseTypeByParentId(UUID parentId, Pageable pageable);

    Page<CaseType> findCaseTypeByParentIdIsNull(final Pageable pageable);

    List<CaseType> findCaseTypeByParentIdIsNotNull();

    CaseType findByDescription(String description);

}
