package org.erpmicroservices.peopleandorganizations.endpoint.graphql.controllers;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.NewCase;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.Case;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.CaseRepository;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.CaseStatusTypeRepository;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.CaseTypeRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.ZonedDateTime;
import java.util.List;


@Controller
public class CaseController {

	private final CaseRepository repository;
	private final CaseTypeRepository caseTypeRepository;
	private final CaseStatusTypeRepository caseStatusTypeRepository;

	public CaseController(final CaseRepository repository, final CaseTypeRepository caseTypeRepository, final CaseStatusTypeRepository caseStatusTypeRepository) {
		this.repository = repository;
		this.caseTypeRepository = caseTypeRepository;
		this.caseStatusTypeRepository = caseStatusTypeRepository;
	}

	@QueryMapping
	public List<Case> cases() {
		return (List<Case>) repository.findAll();
	}

	@MutationMapping
	public Case createCase(@Argument NewCase newCase) {
		return repository.save(Case.builder()
				                       .description(newCase.getDescription())
				                       .caseType(caseTypeRepository.findById(newCase.getCaseTypeId()).get())
				                       .startedAt(ZonedDateTime.parse(newCase.getStartedAt()))
				                       .caseStatusType(caseStatusTypeRepository.findById(newCase.getCaseStatusTypeId()).get())
				                       .build());
	}
}
