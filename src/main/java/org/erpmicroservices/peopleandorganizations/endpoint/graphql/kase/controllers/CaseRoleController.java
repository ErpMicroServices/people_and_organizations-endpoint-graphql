package org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.controllers;

import org.erpmicroservices.peopleandorganizations.backend.entities.CaseRoleEntity;
import org.erpmicroservices.peopleandorganizations.backend.entities.CaseRoleTypeEntity;
import org.erpmicroservices.peopleandorganizations.backend.entities.PartyEntity;
import org.erpmicroservices.peopleandorganizations.backend.repositories.CaseRoleTypeRepository;
import org.erpmicroservices.peopleandorganizations.backend.repositories.PartyRepository;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;


@Controller
public class CaseRoleController {
	private final CaseRoleTypeRepository caseRoleTypeRepository;

	private final PartyRepository partyRepository;

	public CaseRoleController(final CaseRoleTypeRepository caseRoleTypeRepository, final PartyRepository partyRepository) {
		this.caseRoleTypeRepository = caseRoleTypeRepository;
		this.partyRepository = partyRepository;
	}


	@SchemaMapping
	public CaseRoleTypeEntity caseRoleType(CaseRoleEntity caseRoleEntity) {
		return caseRoleTypeRepository.findById(caseRoleEntity.getCaseRoleTypeId()).get();
	}

	@SchemaMapping
	public PartyEntity party(CaseRoleEntity caseRoleEntity) {
		return partyRepository.findById(caseRoleEntity.getPartyId()).get();
	}
}

