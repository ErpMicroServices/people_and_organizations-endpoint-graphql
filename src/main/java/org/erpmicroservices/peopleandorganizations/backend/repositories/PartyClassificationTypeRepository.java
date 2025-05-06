package org.erpmicroservices.peopleandorganizations.backend.repositories;

import org.erpmicroservices.peopleandorganizations.backend.entities.PartyClassificationTypeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface PartyClassificationTypeRepository extends JpaRepository<PartyClassificationTypeEntity, UUID> {

	Page<PartyClassificationTypeEntity> findPartyClassificationTypesByParentId(final UUID parent, final Pageable pageable);

	Page<PartyClassificationTypeEntity> findPartyClassificationTypesByParentIdIsNull(final Pageable pageable);
}
