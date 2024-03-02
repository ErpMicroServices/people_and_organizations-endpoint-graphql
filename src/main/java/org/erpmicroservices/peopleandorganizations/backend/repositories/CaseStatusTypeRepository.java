package org.erpmicroservices.peopleandorganizations.backend.repositories;

import org.erpmicroservices.peopleandorganizations.backend.entities.CaseStatusType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CaseStatusTypeRepository extends JpaRepository<CaseStatusType, UUID> {
    Page<CaseStatusType> findCaseStatusTypeByParentId(UUID parentId, Pageable pageable);

    Page<CaseStatusType> findCaseStatusTypeByParentIdIsNull(final Pageable pageable);

    List<CaseStatusType> findCaseStatusTypeByParentIdIsNotNull();

    CaseStatusType findByDescription(String description);
}
