package org.erpmicroservices.peopleandorganizations.backend.repositories;

import org.erpmicroservices.peopleandorganizations.backend.entities.CaseRoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface CaseRoleRepository extends JpaRepository<CaseRoleEntity, UUID> {

	Page<CaseRoleEntity> findByCaseId(final UUID caseId, final Pageable pageable);
}
