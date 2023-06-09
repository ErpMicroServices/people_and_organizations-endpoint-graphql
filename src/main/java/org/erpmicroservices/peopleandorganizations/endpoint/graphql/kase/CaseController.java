package org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase;

import graphql.relay.Edge;
import jakarta.validation.constraints.NotNull;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.CommunicationEvent;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.NewCommunicationEvent;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.Cursor;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.PageInfo;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.*;
import org.springframework.data.domain.Page;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.erpmicroservices.peopleandorganizations.endpoint.graphql.misc.Utils.pageInfoToPageable;


@Controller
public class CaseController {

	private final CaseRepository caseRepository;

	private final CaseRoleRepository caseRoleRepository;
	private final CaseTypeRepository caseTypeRepository;
	private final CaseStatusTypeRepository caseStatusTypeRepository;
	private final CaseRoleTypeRepository caseRoleTypeRepository;

	private final CommunicationEventRepository communicationEventRepository;

	private final CommunicationEventStatusTypeRepository communicationEventStatusTypeRepository;

	private final CommunicationEventTypeRepository communicationEventTypeRepository;

	private final ContactMechanismTypeRepository contactMechanismTypeRepository;

	private final PartyRelationshipRepository partyRelationshipRepository;
	private final PartyRepository partyRepository;

	public CaseController(final CaseRepository caseRepository, final CaseRoleRepository caseRoleRepository,
	                      final CaseTypeRepository caseTypeRepository,
	                      final CaseStatusTypeRepository caseStatusTypeRepository,
	                      final CaseRoleTypeRepository caseRoleTypeRepository,
	                      final CommunicationEventRepository communicationEventRepository,
	                      final CommunicationEventStatusTypeRepository communicationEventStatusTypeRepository, final CommunicationEventTypeRepository communicationEventTypeRepository, final ContactMechanismTypeRepository contactMechanismTypeRepository, final PartyRelationshipRepository partyRelationshipRepository, final PartyRepository partyRepository) {
		this.caseRepository = caseRepository;
		this.caseRoleRepository = caseRoleRepository;
		this.caseTypeRepository = caseTypeRepository;
		this.caseStatusTypeRepository = caseStatusTypeRepository;
		this.caseRoleTypeRepository = caseRoleTypeRepository;
		this.communicationEventRepository = communicationEventRepository;
		this.communicationEventStatusTypeRepository = communicationEventStatusTypeRepository;
		this.communicationEventTypeRepository = communicationEventTypeRepository;
		this.contactMechanismTypeRepository = contactMechanismTypeRepository;
		this.partyRelationshipRepository = partyRelationshipRepository;
		this.partyRepository = partyRepository;
	}

	@QueryMapping
	public CaseConnection cases(@Argument PageInfo pageInfo) {
		final Page<Case> all = caseRepository.findAll(pageInfoToPageable(pageInfo));
		final List<Edge<Case>> caseEdges = all.get()
				                                   .map(kase -> CaseEdge.builder()
						                                                .node(kase)
						                                                .connectionCursor(Cursor.builder().value(String.valueOf(kase.getId().hashCode())).build())
						                                                .build())
				                                   .collect(Collectors.toList());
		return CaseConnection.builder()
				       .edges(caseEdges)
				       .pageInfo(pageInfo)
				       .build();
	}

	@SchemaMapping
	public CaseType caseType(@NotNull Case kase) {
		return caseTypeRepository.findById(kase.getCaseTypeId()).get();
	}

	@SchemaMapping
	public CaseStatusType caseStatusType(@NotNull Case kase) {
		return caseStatusTypeRepository.findById(kase.getCaseStatusTypeId()).get();
	}

	@SchemaMapping
	public CaseRoleConnection roles(@Argument PageInfo pageInfo, Case kase) {
		final Page<CaseRole> byKase_id = caseRoleRepository.findByCaseId(kase.getId(), pageInfoToPageable(pageInfo));
		final List<Edge<CaseRole>> caseRoleEdges = byKase_id.get()
				                                           .map(caseRole -> CaseRoleEdge.builder()
						                                                            .node(caseRole)
						                                                            .cursor(Cursor.builder().value(String.valueOf(caseRole.getId().hashCode())).build())
						                                                            .build())
				                                           .collect(Collectors.toList());
		return CaseRoleConnection.builder()
				       .edges(caseRoleEdges)
				       .pageInfo(pageInfo)
				       .build();
	}

	@MutationMapping
	public Case createCase(@Argument NewCase newCase) {
		return caseRepository.save(Case.builder()
				                           .caseStatusTypeId(newCase.getCaseStatusTypeId())
				                           .caseTypeId(newCase.getCaseTypeId())
				                           .description(newCase.getDescription())
				                           .startedAt(newCase.getStartedAt())
				                           .build());
	}

	@MutationMapping
	public Case addCaseRole(@Argument NewCaseRole newCaseRole) {
		caseRoleRepository.save(CaseRole.builder()
				                        .caseId(newCaseRole.getCaseId())
				                        .caseRoleTypeId(newCaseRole.getCaseRoleTypeId())
				                        .partyId(newCaseRole.getPartyId())
				                        .fromDate(newCaseRole.getFromDate())
				                        .build());
		return caseRepository.findById(newCaseRole.getCaseId()).get();
	}

	@MutationMapping
	public CaseRole expireCaseRole(@Argument UUID caseId, @Argument UUID caseRoleId) {
		return caseRoleRepository.findById(caseRoleId)
				       .map(kaseRole -> {
					       kaseRole.setThruDate(LocalDate.now());
					       return caseRoleRepository.save(kaseRole);
				       }).orElseThrow();
	}

	@MutationMapping
	public CommunicationEvent addCommunicationEventToCase(@Argument UUID caseId, @Argument NewCommunicationEvent newCommunicationEvent) {
		return communicationEventRepository.save(CommunicationEvent.builder()
				                                         .caseId(caseId)
				                                         .communicationEventStatusTypeId(newCommunicationEvent.getCommunicationEventStatusTypeId())
				                                         .communicationEventTypeId(newCommunicationEvent.getCommunicationEventTypeId())
				                                         .contactMechanismTypeId(newCommunicationEvent.getContactMechanismTypeId())
				                                         .note(newCommunicationEvent.getNote())
				                                         .partyRelationshipId(newCommunicationEvent.getPartyRelationshipId())
				                                         .started(newCommunicationEvent.getStarted())
				                                         .build());
	}
}
