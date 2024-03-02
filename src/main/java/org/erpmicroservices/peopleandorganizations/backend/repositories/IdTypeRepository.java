package org.erpmicroservices.peopleandorganizations.backend.repositories;

import org.erpmicroservices.peopleandorganizations.backend.entities.IdType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface IdTypeRepository extends JpaRepository<IdType, UUID> {
	Page<IdType> findIdTypesByParentId(final UUID parentId, final Pageable pageable);

	Page<IdType> findIdTypesByParentIdIsNull(final Pageable pageable);
}
