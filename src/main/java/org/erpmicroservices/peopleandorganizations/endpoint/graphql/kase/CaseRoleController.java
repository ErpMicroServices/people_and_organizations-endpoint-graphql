package org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.Party;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.CaseRoleTypeRepository;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.PartyRepository;
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
	public CaseRoleType caseRoleType(CaseRole caseRole) {
		return caseRoleTypeRepository.findById(caseRole.getCaseRoleTypeId()).get();
	}

	@SchemaMapping
	public Party party(CaseRole caseRole) {
		return partyRepository.findById(caseRole.getPartyId()).get();
	}
}
