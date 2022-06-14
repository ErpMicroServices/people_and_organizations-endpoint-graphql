package org.erpmicroservices.peopleandorganizations.endpoint.graphql.controllers;

import graphql.relay.Edge;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.*;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.Case;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.CaseRole;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.CommunicationEvent;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.*;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
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

	private final CaseRoleRepository caseRoleRepository;
	private final CaseTypeRepository caseTypeRepository;
	private final CaseStatusTypeRepository caseStatusTypeRepository;
	private final CaseRoleTypeRepository caseRoleTypeRepository;

	private final CommunicationEventRepository communicationEventRepository;
	private final PartyRepository partyRepository;

	public CaseController(final CaseRepository caseRepository, final CaseRoleRepository caseRoleRepository, final CaseTypeRepository caseTypeRepository, final CaseStatusTypeRepository caseStatusTypeRepository, final CaseRoleTypeRepository caseRoleTypeRepository, final CommunicationEventRepository communicationEventRepository, final PartyRepository partyRepository) {
		this.caseRepository = caseRepository;
		this.caseRoleRepository = caseRoleRepository;
		this.caseTypeRepository = caseTypeRepository;
		this.caseStatusTypeRepository = caseStatusTypeRepository;
		this.caseRoleTypeRepository = caseRoleTypeRepository;
		this.communicationEventRepository = communicationEventRepository;
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

	@SchemaMapping
	public CaseRoleConnection roles(Case kase) {
		final List<Edge<CaseRole>> caseRoleEdges = caseRoleRepository.findByKase_Id(kase.getId())
				                                           .stream().map(caseRole -> CaseRoleEdge.builder()
						                                                                     .node(caseRole)
						                                                                     .cursor(Cursor.builder().value(String.valueOf(caseRole.getId().hashCode())).build())
						                                                                     .build())
				                                           .collect(Collectors.toList());
		return CaseRoleConnection.builder()
				       .edges(caseRoleEdges)
				       .pageInfo(PageInfo.builder()
						                 .hasNextPage(false)
						                 .hasPreviousPage(false)
						                 .startCursor(caseRoleEdges.isEmpty() ? null : caseRoleEdges.get(0).getCursor())
						                 .endCursor(caseRoleEdges.isEmpty() ? null : caseRoleEdges.get(caseRoleEdges.size() - 1).getCursor())
						                 .build())
				       .build();
	}

	@SchemaMapping
	public CommunicationEventConnection communicationEvents(Case kase) {
		final List<Edge<CommunicationEvent>> communicationEventEdges = communicationEventRepository.findByKase_Id(kase.getId()).stream()
				                                                               .map(communicationEvent -> CommunicationEventEdge.builder()
						                                                                                          .node(communicationEvent)
						                                                                                          .cursor(Cursor.builder().value(String.valueOf(communicationEvent.getId().hashCode())).build())
						                                                                                          .build())
				                                                               .collect(Collectors.toList());
		return CommunicationEventConnection.builder()
				       .edges(communicationEventEdges)
				       .pageInfo(PageInfo.builder()
						                 .hasNextPage(false)
						                 .hasPreviousPage(false)
						                 .startCursor(communicationEventEdges.isEmpty() ? null : communicationEventEdges.get(0).getCursor())
						                 .endCursor(communicationEventEdges.isEmpty() ? null : communicationEventEdges.get(communicationEventEdges.size() - 1).getCursor())
						                 .build())
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
	public CaseRole addCaseRole(@Argument NewCaseRole newCaseRole) {
		return caseRoleTypeRepository.findById(newCaseRole.getCaseRoleTypeId())
				       .flatMap(caseRoleType ->
						                partyRepository.findById(newCaseRole.getPartyId())
								                .flatMap(party -> {
									                return caseRepository.findById(newCaseRole.getCaseId())
											                       .flatMap(kase -> {
												                       return Optional.of(caseRoleRepository.save(CaseRole.builder()
														                                                                  .caseRoleType(caseRoleType)
														                                                                  .party(party)
														                                                                  .fromDate(LocalDate.parse(newCaseRole.getFromDate()))
														                                                                  .build()));
											                       });
								                })).orElseThrow();
	}

	@MutationMapping
	public CaseRole expireCaseRole(@Argument UUID caseId, @Argument UUID caseRoleId) {
		return caseRoleRepository.findById(caseRoleId)
				       .map(kaseRole -> {
					       kaseRole.setThruDate(LocalDate.now());
					       return caseRoleRepository.save(kaseRole);
				       }).orElseThrow();
	}
}
