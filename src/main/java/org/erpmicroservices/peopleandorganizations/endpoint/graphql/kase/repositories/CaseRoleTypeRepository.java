package org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.models.CaseRoleType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.List;
import java.util.UUID;

@GraphQlRepository
public interface CaseRoleTypeRepository extends JpaRepository<CaseRoleType, UUID> {

	Page<CaseRoleType> findCaseRoleTypesByParentId(final UUID parentId, final Pageable pageable);

	Page<CaseRoleType> findCaseRoleTypesByParentIdIsNull(final Pageable pageable);

	List<CaseRoleType> findCaseRoleTypeByParentIdIsNotNull();
}
