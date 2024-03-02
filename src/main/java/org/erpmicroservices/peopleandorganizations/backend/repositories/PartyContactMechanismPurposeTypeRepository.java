package org.erpmicroservices.peopleandorganizations.backend.repositories;

import org.erpmicroservices.peopleandorganizations.backend.entities.PartyContactMechanismPurposeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface PartyContactMechanismPurposeTypeRepository extends JpaRepository<PartyContactMechanismPurposeType, UUID> {
	Page<PartyContactMechanismPurposeType> findPartyContactMechanismPurposeTypesByParentId(final UUID parentId, final Pageable pageable);

	Page<PartyContactMechanismPurposeType> findPartyContactMechanismPurposeTypesByParentIdIsNull(final Pageable pageable);
}