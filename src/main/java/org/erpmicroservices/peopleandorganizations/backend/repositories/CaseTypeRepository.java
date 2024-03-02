package org.erpmicroservices.peopleandorganizations.backend.repositories;

import org.erpmicroservices.peopleandorganizations.backend.entities.CaseType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CaseTypeRepository extends JpaRepository<CaseType, UUID> {
    Page<CaseType> findCaseTypeByParentId(UUID parentId, Pageable pageable);

    Page<CaseType> findCaseTypeByParentIdIsNull(final Pageable pageable);

    List<CaseType> findCaseTypeByParentIdIsNotNull();

    CaseType findByDescription(String description);

}
