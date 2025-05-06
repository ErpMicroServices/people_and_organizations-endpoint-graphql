package org.erpmicroservices.peopleandorganizations.backend.repositories;

import org.erpmicroservices.peopleandorganizations.backend.entities.CaseStatusTypeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CaseStatusTypeRepository extends JpaRepository<CaseStatusTypeEntity, UUID> {
    Page<CaseStatusTypeEntity> findCaseStatusTypeByParentId(UUID parentId, Pageable pageable);

    Page<CaseStatusTypeEntity> findCaseStatusTypeByParentIdIsNull(final Pageable pageable);

    List<CaseStatusTypeEntity> findCaseStatusTypeByParentIdIsNotNull();

    CaseStatusTypeEntity findByDescription(String description);
}
