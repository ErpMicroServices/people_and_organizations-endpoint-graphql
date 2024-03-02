package org.erpmicroservices.peopleandorganizations.backend.repositories;

import org.erpmicroservices.peopleandorganizations.backend.entities.PartyClassificationType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface PartyClassificationTypeRepository extends JpaRepository<PartyClassificationType, UUID> {

	Page<PartyClassificationType> findPartyClassificationTypesByParentId(final UUID parent, final Pageable pageable);

	Page<PartyClassificationType> findPartyClassificationTypesByParentIdIsNull(final Pageable pageable);
}
