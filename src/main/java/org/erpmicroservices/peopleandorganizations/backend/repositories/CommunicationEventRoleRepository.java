package org.erpmicroservices.peopleandorganizations.backend.repositories;

import org.erpmicroservices.peopleandorganizations.backend.entities.CommunicationEventRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface CommunicationEventRoleRepository extends PagingAndSortingRepository<CommunicationEventRole, UUID> {
	Page<CommunicationEventRole> findByCommunicationEventId(final UUID communicationEventId, final Pageable pageable);
}
