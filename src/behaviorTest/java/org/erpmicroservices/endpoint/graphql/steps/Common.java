package org.erpmicroservices.endpoint.graphql.steps;

import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import org.erpmicroservices.endpoint.graphql.CucumberSpringBootContext;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.model.CommunicationEventStatusType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.repositories.*;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.models.CaseStatusType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.models.CaseType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.repositories.*;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.contactmechanism.PartyContactMechanismPurposeRepository;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.role.PartyRoleType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.type.PartyType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.*;
import org.springframework.data.domain.Pageable;
import org.springframework.graphql.test.tester.GraphQlTester;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

public class Common extends CucumberSpringBootContext {

    private final List<CaseType> caseTypes = new ArrayList<>();

    public Common(CaseStatusTypeRepository caseStatusTypeRepository, CaseTypeRepository caseTypeRepository, CaseRepository caseRepository, PartyTypeRepository partyTypeRepository, PartyRepository partyRepository, CaseRoleTypeRepository caseRoleTypeRepository, CaseRoleRepository caseRoleRepository, ContactMechanismTypeRepository contactMechanismTypeRepository, PartyRoleTypeRepository partyRoleTypeRepository, PartyRoleRepository partyRoleRepository, CommunicationEventStatusTypeRepository communicationEventStatusTypeRepository, CommunicationEventTypeRepository communicationEventTypeRepository, PartyRelationshipTypeRepository partyRelationshipTypeRepository, PartyRelationshipStatusTypeRepository partyRelationshipStatusTypeRepository, PriorityTypeRepository priorityTypeRepository, PartyRelationshipRepository partyRelationshipRepository, CommunicationEventRepository communicationEventRepository, FacilityRepository facilityRepository, FacilityTypeRepository facilityTypeRepository, FacilityRoleTypeRepository facilityRoleTypeRepository, FacilityRoleRepository facilityRoleRepository, FacilityContactMechanismRepository facilityContactMechanismRepository, ContactMechanismRepository contactMechanismRepository, GeographicBoundaryRepository geographicBoundaryRepository, GeographicBoundaryTypeRepository geographicBoundaryTypeRepository, ContactMechanismGeographicBoundaryRepository contactMechanismGeographicBoundaryRepository, PartyContactMechanismRepository partyContactMechanismRepository, PartyContactMechanismPurposeRepository partyContactMechanismPurposeRepository, PartyContactMechanismPurposeTypeRepository partyContactMechanismPurposeTypeRepository, CommunicationEventPurposeTypeRepository communicationEventPurposeTypeRepository, CommunicationEventRoleTypeRepository communicationEventRoleTypeRepository, GraphQlTester graphQlTester) {
        super(caseStatusTypeRepository, caseTypeRepository, caseRepository, partyTypeRepository, partyRepository, caseRoleTypeRepository, caseRoleRepository, contactMechanismTypeRepository, partyRoleTypeRepository, partyRoleRepository, communicationEventStatusTypeRepository, communicationEventTypeRepository, partyRelationshipTypeRepository, partyRelationshipStatusTypeRepository, priorityTypeRepository, partyRelationshipRepository, communicationEventRepository, facilityRepository, facilityTypeRepository, facilityRoleTypeRepository, facilityRoleRepository, facilityContactMechanismRepository, contactMechanismRepository, geographicBoundaryRepository, geographicBoundaryTypeRepository, contactMechanismGeographicBoundaryRepository, partyContactMechanismRepository, partyContactMechanismPurposeRepository, partyContactMechanismPurposeTypeRepository, communicationEventPurposeTypeRepository, communicationEventRoleTypeRepository, graphQlTester);
    }

    @BeforeAll
    public static void setupWorld() {
        postgresqlDbContainer.start();
    }

    @AfterAll
    public static void tearDownWorld() {
        postgresqlDbContainer.stop();
    }

    @Before
    public void setupTheScenario() {
        theDatabaseIsEmpty();
    }

    @Given("the following types:")
    public void the_following_types(io.cucumber.datatable.DataTable dataTable) {
        final List<List<String>> dataTableLists = dataTable.asLists();
        dataTableLists
                .forEach(row -> {
                    switch (row.get(0)) {
                        case "case" -> caseTypeRepository.save(CaseType.builder()
                                .description(row.get(1))
                                .build());
                        case "case status" -> caseStatusTypeRepository.save(CaseStatusType.builder()
                                .description(row.get(1))
                                .build());
                        default -> fail("Unknown type: " + row);

                    }
                });
    }

    private void theDatabaseIsEmpty() {
        communicationEventRepository.deleteAll();
        partyRelationshipRepository.deleteAll();
        caseRoleRepository.deleteAll();
        partyRoleRepository.deleteAll();
        facilityRoleRepository.deleteAll();
        facilityContactMechanismRepository.deleteAll();
        partyContactMechanismRepository.deleteAll();
        contactMechanismGeographicBoundaryRepository.deleteAll();
        contactMechanismRepository.deleteAll();
        geographicBoundaryRepository.deleteAll();


        partyRepository.deleteAll();
        caseRepository.deleteAll();
        communicationEventRepository.deleteAll();
        facilityRepository.deleteAll();

        caseRoleTypeRepository.deleteAll(caseRoleTypeRepository.findCaseRoleTypeByParentIdIsNotNull().stream().toList());
        caseRoleTypeRepository.deleteAll();
        caseStatusTypeRepository.deleteAll(caseStatusTypeRepository.findCaseStatusTypeByParentIdIsNotNull().stream().toList());
        caseStatusTypeRepository.deleteAll();

        caseTypeRepository.deleteAll(caseTypeRepository.findCaseTypeByParentIdIsNotNull().stream().toList());
        caseTypeRepository.deleteAll();

        partyTypeRepository.findPartyTypesByParentIdIsNull(Pageable.unpaged()).forEach(this::deletePartyTypeChildren);
        partyTypeRepository.deleteAll();

        partyRelationshipTypeRepository.deleteAll();
        priorityTypeRepository.deleteAll();
        partyRelationshipStatusTypeRepository.deleteAll();
        partyRelationshipTypeRepository.deleteAll();

        partyRoleTypeRepository.findPartyRoleTypesByParentIdIsNull(Pageable.unpaged()).forEach(prt -> {
            deletePartyRoleTypeChildren(prt);
            partyRoleTypeRepository.delete(prt);
        });
        partyRoleTypeRepository.deleteAll();

        communicationEventStatusTypeRepository.findCommunicationEventStatusTypeByParentIdIsNull(Pageable.unpaged()).forEach(root -> {
            deleteCommunicationEventStatusTypeChildren(root);
            communicationEventStatusTypeRepository.delete(root);
        });

        contactMechanismTypeRepository.deleteAll();
        facilityRoleTypeRepository.deleteAll();
        facilityTypeRepository.deleteAll();
        geographicBoundaryTypeRepository.deleteAll();
        partyContactMechanismPurposeRepository.deleteAll();
        communicationEventPurposeTypeRepository.deleteAll(communicationEventPurposeTypeRepository.findCommunicationEventPurposeTypeByParentIdIsNotNull().stream().toList());
        communicationEventPurposeTypeRepository.deleteAll();
        communicationEventRoleTypeRepository.deleteAll(communicationEventRoleTypeRepository.findCommunicationEventRoleTypeByParentIdIsNotNull().stream().toList());
        communicationEventRoleTypeRepository.deleteAll();
    }

    private void deletePartyTypeChildren(PartyType root) {
        partyTypeRepository.findPartyTypesByParentId(root.getId(), Pageable.unpaged()).forEach(this::deletePartyTypeChildren);
        partyTypeRepository.delete(root);
    }

    private void deleteCommunicationEventStatusTypeChildren(CommunicationEventStatusType root) {
        communicationEventStatusTypeRepository.findCommunicationEventStatusTypeByParentId(root.getParentId(), Pageable.unpaged()).stream()
                .forEach(this::deleteCommunicationEventStatusTypeChildren);
        communicationEventStatusTypeRepository.delete(root);
    }

    private void deletePartyRoleTypeChildren(PartyRoleType root) {
        partyRoleTypeRepository.findPartyRoleTypesByParentId(root.getId(), Pageable.unpaged()).stream()
                .forEach(this::deletePartyRoleTypeChildren);
        partyRoleTypeRepository.delete(root);
    }
}
