package org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.CaseStatusType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.List;
import java.util.UUID;

@GraphQlRepository
public interface CaseStatusTypeRepository extends JpaRepository<CaseStatusType, UUID> {
	Page<CaseStatusType> findCaseStatusTypeByParentId(UUID parentId, Pageable pageable);

	Page<CaseStatusType> findCaseStatusTypeByParentIdIsNull(final Pageable pageable);

	List<CaseStatusType> findCaseStatusTypeByParentIdIsNotNull();
}
