package org.erpmicroservices.peopleandorganizations.backend.repositories;

import org.erpmicroservices.peopleandorganizations.backend.entities.NameTypeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface NameTypeRepository extends JpaRepository<NameTypeEntity, UUID> {
	Page<NameTypeEntity> findNameTypesByParentId(final UUID parentId, final Pageable pageable);

	Page<NameTypeEntity> findNameTypesByParentIdIsNull(final Pageable pageable);
}
