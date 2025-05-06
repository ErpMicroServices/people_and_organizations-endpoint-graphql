package org.erpmicroservices.peopleandorganizations.backend.repositories;

import org.erpmicroservices.peopleandorganizations.backend.entities.PartyIdEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface PartyIdRepository extends JpaRepository<PartyIdEntity, UUID> {

	Page<PartyIdEntity> findPartyIdsByPartyId(final UUID parentId, final Pageable pageable);

}
