package org.erpmicroservices.peopleandorganizations.endpoint.graphql.controllers;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.CaseRoleType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.CaseRoleTypeRepository;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class CaseRoleTypeController {

	private final CaseRoleTypeRepository caseRoleTypeRepository;

	public CaseRoleTypeController(final CaseRoleTypeRepository caseRoleTypeRepository) {
		this.caseRoleTypeRepository = caseRoleTypeRepository;
	}

	@QueryMapping
	public Iterable<CaseRoleType> caseRoleTypes() {
		return caseRoleTypeRepository.findAll();
	}
}
