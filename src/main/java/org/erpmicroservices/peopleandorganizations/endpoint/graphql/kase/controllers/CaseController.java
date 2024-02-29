package org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.controllers;

import graphql.relay.Edge;
import jakarta.validation.constraints.NotNull;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.model.CommunicationEvent;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.model.NewCommunicationEvent;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.repositories.CommunicationEventRepository;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.repositories.CommunicationEventStatusTypeRepository;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.repositories.CommunicationEventTypeRepository;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.Cursor;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.PageInfo;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.graphql.*;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.models.Case;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.models.CaseRole;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.models.CaseStatusType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.models.CaseType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.repositories.*;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.ContactMechanismTypeRepository;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.PartyRelationshipRepository;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.PartyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    Logger logger = LoggerFactory.getLogger(CaseController.class);
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
    public CaseConnection casesByCaseType(@Argument CaseType caseType, @Argument PageInfo pageInfo) {
        final Page<Case> casesPage = caseRepository.findByCaseTypeId(caseType.getId(), pageInfoToPageable(pageInfo));
        return getCaseConnection(casesPage);
    }

    @QueryMapping
    public CaseConnection casesByCaseStatusType(@Argument CaseStatusType caseStatusType, @Argument PageInfo pageInfo) {
        final Page<Case> casesPage = caseRepository.findByCaseStatusTypeId(caseStatusType.getId(), pageInfoToPageable(pageInfo));
        return getCaseConnection(casesPage);
    }

    private CaseConnection getCaseConnection(Page<Case> casesPage) {
        List<CaseEdge> caseEdges = casesPage.stream()
                .map(kase -> CaseEdge.builder()
                        .cursor(Cursor.builder().value(String.valueOf(kase.getId().hashCode())).build())
                        .node(kase)
                        .build())
                .toList();
        return CaseConnection.builder()
                .pageInfo(PageInfo.builder()
                        .pageNumber(casesPage.getNumber())
                        .pageSize(casesPage.getNumberOfElements())
                        .build())
                .edges(caseEdges)
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
