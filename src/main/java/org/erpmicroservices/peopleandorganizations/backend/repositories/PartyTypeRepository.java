package org.erpmicroservices.peopleandorganizations.backend.repositories;

import org.erpmicroservices.peopleandorganizations.backend.entities.PartyType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PartyTypeRepository extends JpaRepository<PartyType, UUID> {
	Page<PartyType> findPartyTypesByParentId(final UUID parent, final Pageable pageable);

	Page<PartyType> findPartyTypesByParentIdIsNull(final Pageable pageable);

	List<PartyType> findPartyTypesByParentIdIsNotNull();
}
