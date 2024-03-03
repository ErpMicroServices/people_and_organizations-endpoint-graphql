package org.erpmicroservices.peopleandorganizations.backend.repositories;

import org.erpmicroservices.peopleandorganizations.backend.entities.PartyTypeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PartyTypeRepository extends JpaRepository<PartyTypeEntity, UUID> {
	Page<PartyTypeEntity> findPartyTypesByParentId(final UUID parent, final Pageable pageable);

	Page<PartyTypeEntity> findPartyTypesByParentIdIsNull(final Pageable pageable);

	List<PartyTypeEntity> findPartyTypesByParentIdIsNotNull();
}
