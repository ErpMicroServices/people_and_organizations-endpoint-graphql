package org.erpmicroservices.peopleandorganizations.backend.repositories;

import org.erpmicroservices.peopleandorganizations.backend.entities.CaseRoleType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CaseRoleTypeRepository extends JpaRepository<CaseRoleType, UUID> {

	Page<CaseRoleType> findCaseRoleTypesByParentId(final UUID parentId, final Pageable pageable);

	Page<CaseRoleType> findCaseRoleTypesByParentIdIsNull(final Pageable pageable);

	List<CaseRoleType> findCaseRoleTypeByParentIdIsNotNull();
}
