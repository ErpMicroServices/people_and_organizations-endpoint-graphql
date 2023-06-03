package org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.id.IdType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;

import java.util.UUID;


@GraphQlRepository
public interface IdTypeRepository extends JpaRepository<IdType, UUID> {
	Page<IdType> findIdTypesByParentId(final UUID parentId, final Pageable pageable);

	Page<IdType> findIdTypesByParentIdIsNull(final Pageable pageable);
}
