package org.erpmicroservices.peopleandorganizations.endpoint.graphql.controllers;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.CaseStatusType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.CaseStatusTypeRepository;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CaseStatusTypeController {

	private final CaseStatusTypeRepository caseStatusTypeRepository;

	public CaseStatusTypeController(final CaseStatusTypeRepository caseStatusTypeRepository) {
		this.caseStatusTypeRepository = caseStatusTypeRepository;
	}

	@QueryMapping
	public List<CaseStatusType> caseStatusTypes() {
		return (List<CaseStatusType>) caseStatusTypeRepository.findAll();
	}
}
