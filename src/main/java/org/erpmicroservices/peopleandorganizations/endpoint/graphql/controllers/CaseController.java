package org.erpmicroservices.peopleandorganizations.endpoint.graphql.controllers;

import graphql.relay.Edge;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.*;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.Case;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.CaseRole;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.*;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Controller
public class CaseController {

	private final CaseRepository caseRepository;
	private final CaseTypeRepository caseTypeRepository;
	private final CaseStatusTypeRepository caseStatusTypeRepository;
	private final CaseRoleTypeRepository caseRoleTypeRepository;

	private final PartyRepository partyRepository;

	public CaseController(final CaseRepository caseRepository, final CaseTypeRepository caseTypeRepository, final CaseStatusTypeRepository caseStatusTypeRepository, final CaseRoleTypeRepository caseRoleTypeRepository, final PartyRepository partyRepository) {
		this.caseRepository = caseRepository;
		this.caseTypeRepository = caseTypeRepository;
		this.caseStatusTypeRepository = caseStatusTypeRepository;
		this.caseRoleTypeRepository = caseRoleTypeRepository;
		this.partyRepository = partyRepository;
	}

	@QueryMapping
	public CaseConnection cases() {
		final List<Edge<Case>> caseEdges = ((List<Case>) caseRepository.findAll()).stream()
				                                   .map(kase -> CaseEdge.builder()
						                                                .node(kase)
						                                                .connectionCursor(Cursor.builder().value(String.valueOf(kase.getId().hashCode())).build())
						                                                .build())
				                                   .collect(Collectors.toList());
		final PageInfo pageInfo = PageInfo.builder()
				                          .hasNextPage(false)
				                          .hasPreviousPage(false)
				                          .startCursor(caseEdges.get(0).getCursor())
				                          .endCursor(caseEdges.get(caseEdges.size() - 1).getCursor())
				                          .build();
		return CaseConnection.builder()
				       .edges(caseEdges)
				       .pageInfo(pageInfo)
				       .build();
	}

	@MutationMapping
	public Case createCase(@Argument NewCase newCase) {
		return caseTypeRepository.findById(newCase.getCaseTypeId())
				       .flatMap(caseType ->
						                caseStatusTypeRepository.findById(newCase.getCaseStatusTypeId())
								                .flatMap(caseStatusType ->
										                         Optional.of(caseRepository.save(Case.builder()
												                                                         .description(newCase.getDescription())
												                                                         .caseType(caseType)
												                                                         .startedAt(ZonedDateTime.parse(newCase.getStartedAt()))
												                                                         .caseStatusType(caseStatusType)
												                                                         .build())))).orElseThrow();
	}

	@MutationMapping
	public Case addCaseRole(@Argument NewCaseRole newCaseRole) {
		return caseRoleTypeRepository.findById(newCaseRole.getCaseRoleTypeId())
				       .flatMap(caseRoleType ->
						                partyRepository.findById(newCaseRole.getPartyId())
								                .flatMap(party ->
										                         caseRepository.findById(newCaseRole.getCaseId())
												                         .flatMap(kase -> {
													                         kase.addRole(CaseRole.builder()
															                                      .caseRoleType(caseRoleType)
															                                      .party(party)
															                                      .fromDate(LocalDate.parse(newCaseRole.getFromDate()))
															                                      .build());
													                         return Optional.of(caseRepository.save(kase));
												                         }))).orElseThrow();
	}

	@MutationMapping
	public Case expireCaseRole(@Argument UUID caseId, @Argument UUID caseRoleId) {
		return caseRepository.findById(caseId)
				       .map(kase -> {
					       kase.expireRoleWithId(caseRoleId);
					       return caseRepository.save(kase);
				       }).orElseThrow();
	}
}
