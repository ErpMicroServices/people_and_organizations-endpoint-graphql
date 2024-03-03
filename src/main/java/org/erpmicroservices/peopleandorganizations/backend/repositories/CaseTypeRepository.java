package org.erpmicroservices.peopleandorganizations.backend.repositories;

import org.erpmicroservices.peopleandorganizations.backend.entities.CaseTypeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CaseTypeRepository extends JpaRepository<CaseTypeEntity, UUID> {
    Page<CaseTypeEntity> findCaseTypeByParentId(UUID parentId, Pageable pageable);

    Page<CaseTypeEntity> findCaseTypeByParentIdIsNull(final Pageable pageable);

    List<CaseTypeEntity> findCaseTypeByParentIdIsNotNull();

    CaseTypeEntity findByDescription(String description);

}
