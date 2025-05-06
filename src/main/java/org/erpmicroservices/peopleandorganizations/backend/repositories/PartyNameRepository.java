package org.erpmicroservices.peopleandorganizations.backend.repositories;

import org.erpmicroservices.peopleandorganizations.backend.entities.PartyNameEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface PartyNameRepository extends JpaRepository<PartyNameEntity, UUID> {

	Page<PartyNameEntity> findPartyNamesByPartyId(final UUID parentId, final Pageable pageable);

}
