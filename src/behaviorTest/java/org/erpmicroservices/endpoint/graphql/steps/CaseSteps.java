package org.erpmicroservices.endpoint.graphql.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.erpmicroservices.endpoint.graphql.CucumberSpringBootContext;
import org.erpmicroservices.peopleandorganizations.backend.entities.CaseEntity;
import org.erpmicroservices.peopleandorganizations.backend.entities.CaseStatusTypeEntity;
import org.erpmicroservices.peopleandorganizations.backend.entities.CaseTypeEntity;
import org.erpmicroservices.peopleandorganizations.backend.repositories.*;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.models.CaseNodeEdge;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.models.CaseRepository;
import org.erpmicroservices.peopleandorganizations.backend.repositories.CaseRoleRepository;
import org.erpmicroservices.peopleandorganizations.backend.repositories.CaseRoleTypeRepository;
import org.erpmicroservices.peopleandorganizations.backend.repositories.CaseStatusTypeRepository;
import org.erpmicroservices.peopleandorganizations.backend.repositories.CaseTypeRepository;
import org.erpmicroservices.peopleandorganizations.backend.repositories.PartyContactMechanismPurposeRepository;
import org.springframework.graphql.test.tester.GraphQlTester;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class CaseSteps extends CucumberSpringBootContext {
    private final List<CaseEntity> expectedCaseEntities = new ArrayList<>();
    private List<CaseNodeEdge> actualCases;

    private CaseEntity expectedCaseEntity;
    private CaseEntity actualCaseEntity;

    private final CaseEntity.CaseEntityBuilder caseEntityBuilder = CaseEntity.builder();

    public CaseSteps(CaseStatusTypeRepository caseStatusTypeRepository, CaseTypeRepository caseTypeRepository, CaseRepository caseRepository, PartyTypeRepository partyTypeRepository, PartyRepository partyRepository, CaseRoleTypeRepository caseRoleTypeRepository, CaseRoleRepository caseRoleRepository, ContactMechanismTypeRepository contactMechanismTypeRepository, PartyRoleTypeRepository partyRoleTypeRepository, PartyRoleRepository partyRoleRepository, CommunicationEventStatusTypeRepository communicationEventStatusTypeRepository, CommunicationEventTypeRepository communicationEventTypeRepository, PartyRelationshipTypeRepository partyRelationshipTypeRepository, PartyRelationshipStatusTypeRepository partyRelationshipStatusTypeRepository, PriorityTypeRepository priorityTypeRepository, PartyRelationshipRepository partyRelationshipRepository, CommunicationEventRepository communicationEventRepository, FacilityRepository facilityRepository, FacilityTypeRepository facilityTypeRepository, FacilityRoleTypeRepository facilityRoleTypeRepository, FacilityRoleRepository facilityRoleRepository, FacilityContactMechanismRepository facilityContactMechanismRepository, ContactMechanismRepository contactMechanismRepository, GeographicBoundaryRepository geographicBoundaryRepository, GeographicBoundaryTypeRepository geographicBoundaryTypeRepository, ContactMechanismGeographicBoundaryRepository contactMechanismGeographicBoundaryRepository, PartyContactMechanismRepository partyContactMechanismRepository, PartyContactMechanismPurposeRepository partyContactMechanismPurposeRepository, PartyContactMechanismPurposeTypeRepository partyContactMechanismPurposeTypeRepository, CommunicationEventPurposeTypeRepository communicationEventPurposeTypeRepository, CommunicationEventRoleTypeRepository communicationEventRoleTypeRepository, GraphQlTester graphQlTester) {
        super(caseStatusTypeRepository, caseTypeRepository, caseRepository, partyTypeRepository, partyRepository, caseRoleTypeRepository, caseRoleRepository, contactMechanismTypeRepository, partyRoleTypeRepository, partyRoleRepository, communicationEventStatusTypeRepository, communicationEventTypeRepository, partyRelationshipTypeRepository, partyRelationshipStatusTypeRepository, priorityTypeRepository, partyRelationshipRepository, communicationEventRepository, facilityRepository, facilityTypeRepository, facilityRoleTypeRepository, facilityRoleRepository, facilityContactMechanismRepository, contactMechanismRepository, geographicBoundaryRepository, geographicBoundaryTypeRepository, contactMechanismGeographicBoundaryRepository, partyContactMechanismRepository, partyContactMechanismPurposeRepository, partyContactMechanismPurposeTypeRepository, communicationEventPurposeTypeRepository, communicationEventRoleTypeRepository, graphQlTester);
    }

    @Given("there are {int} cases with a type of {string} with a status of {string} in the database")
    public void there_are_cases_with_a_type_of_with_a_status_of_in_the_database(Integer numberOfCases, String caseTypeDescription, String caseStatusDescription) {
        final CaseTypeEntity caseTypeEntity = caseTypeRepository.findByDescription(caseTypeDescription);
        final CaseStatusTypeEntity caseStatusTypeEntity = caseStatusTypeRepository.findByDescription(caseStatusDescription);

        for (int i = 0; i < numberOfCases; i++) {
            expectedCaseEntities.add(caseRepository.save(CaseEntity.builder()
                    .caseStatusTypeId(caseStatusTypeEntity.getId())
                    .caseTypeId(caseTypeEntity.getId())
                    .description("CaseEntity number " + i + " of " + numberOfCases)
                    .startedAt(ZonedDateTime.now())
                    .build()));
        }
    }

    @Given("a case description of {string}")
    public void a_case_description_of(String description) {
        caseEntityBuilder.description(description);
    }

    @Given("a case status of {string}")
    public void a_case_status_of(String caseStatusDescription) {
        final CaseStatusTypeEntity caseStatusTypeEntity = caseStatusTypeRepository.findByDescription(caseStatusDescription);
        caseEntityBuilder.caseStatusTypeId(caseStatusTypeEntity.getId());
    }

    @Given("a case type of {string}")
    public void a_case_type_of(String caseTypeDescription) {
        final CaseTypeEntity caseTypeEntity = caseTypeRepository.findByDescription(caseTypeDescription);
        caseEntityBuilder.caseTypeId(caseTypeEntity.getId());
    }

    @Given("a case was started at {string}")
    public void a_case_was_started_at(String isoDateTime) {

        final ZonedDateTime zonedDateTime = ZonedDateTime.parse(isoDateTime, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        caseEntityBuilder.startedAt(zonedDateTime);
    }

    @When("I save the case")
    public void i_save_the_case() {
        expectedCaseEntity = caseEntityBuilder.build();
        actualCaseEntity = graphQlTester.documentName("CaseCreate")
                .operationName("CreateCase")
                .variable("newCase", Map.of(
                        "caseStatusTypeId", expectedCaseEntity.getCaseStatusTypeId(),
                        "caseTypeId", expectedCaseEntity.getCaseTypeId(),
                        "description", expectedCaseEntity.getDescription(),
                        "startedAt", expectedCaseEntity.getStartedAt()
                ))
                .execute()
                .path("createCase")
                .entity(CaseEntity.class)
                .get();
    }

    @When("I search for all cases")
    public void i_search_for_all_cases() {
        actualCases = this.graphQlTester.documentName("caseQuery")
                .operationName("caseQuery")
                .variable("rolesPageInfo", pageInfoSortingOn("fromDate"))
                .variable("communicationEventPageInfo", pageInfoSortingOn("started"))
                .variable("casePageInfo", pageInfoSortingOn("description"))
                .execute()
                .path("cases.edges")
                .entityList(CaseNodeEdge.class)
                .get();
    }

    @When("I search for cases of type {string}")
    public void i_search_for_cases_of_type(String description) {
        final CaseTypeEntity caseTypeEntity = caseTypeRepository
                .findByDescription(description);
        actualCases = this.graphQlTester.documentName("casesByCaseType")
                .operationName("casesByCaseType")
                .variable("caseTypeEntity", Map.of(
                        "id", caseTypeEntity.getId(),
                        "description", caseTypeEntity.getDescription()
                ))
                .variable("pageInfo", pageInfoSortingOn("description"))
                .execute()
                .path("casesByCaseType.edges")
                .entityList(CaseNodeEdge.class)
                .get();
    }

    @When("I search for cases with a status of {string}")
    public void i_search_for_cases_with_a_status_of(String statusDescription) {
        final CaseStatusTypeEntity caseStatusTypeEntity = caseStatusTypeRepository.findByDescription(statusDescription);
        actualCases = graphQlTester.documentName("CasesByCaseStatusType")
                .operationName("CasesByCaseStatusType")
                .variable("caseStatusTypeEntity", Map.of("id", caseStatusTypeEntity.getId(),
                        "description", caseStatusTypeEntity.getDescription()))
                .variable("pageInfo", pageInfoSortingOn("description"))
                .execute()
                .path("casesByCaseStatusType.edges")
                .entityList(CaseNodeEdge.class)
                .get();
    }

    @Then("I get {int} cases")
    public void i_get_cases(Integer expectedSize) {
        assertEquals(expectedSize.intValue(), actualCases.size());

    }

    @Then("{int} of them are cases of type {string}")
    public void of_them_are_cases_of_type(Integer expectedNumberOfCases, String caseTypeDescription) {
        final CaseTypeEntity caseTypeEntity = caseTypeRepository.findByDescription(caseTypeDescription);
        assertEquals(expectedNumberOfCases.intValue(),
                actualCases
                        .stream()
                        .filter(caseNodeEdge -> caseNodeEdge
                                .getNode()
                                .getCaseType()
                                .getId()
                                .equals(caseTypeEntity.getId())).toList().size());
    }

    @Then("{int} of them are cases in status {string}")
    public void of_them_are_cases_in_status(Integer expectedNumberOfCases, String statusDescription) {
        final CaseStatusTypeEntity caseStatusTypeEntity = caseStatusTypeRepository.findByDescription(statusDescription);
        assertEquals(expectedNumberOfCases.intValue(),
                actualCases
                        .stream()
                        .filter(caseNodeEdge -> caseNodeEdge
                                .getNode()
                                .getCaseStatusType()
                                .getId()
                                .equals(caseStatusTypeEntity.getId())).toList().size());
    }

    @Then("the operation was successful")
    public void the_operation_was_successful() {
        assertNotNull(actualCaseEntity);
    }

    @Then("the case is in the database")
    public void the_case_is_in_the_database() {
        caseRepository
                .findById(actualCaseEntity.getId())
                .ifPresentOrElse(c -> {
                            assertNotNull(c.getId());
                            assertEquals(expectedCaseEntity.getDescription(), c.getDescription());
                            assertEquals(expectedCaseEntity.getStartedAt().withZoneSameInstant(ZoneId.of("UTC")), c.getStartedAt().withZoneSameInstant(ZoneId.of("UTC")));
                            assertEquals(expectedCaseEntity.getCaseStatusTypeId(), c.getCaseStatusTypeId());
                            assertEquals(expectedCaseEntity.getCaseTypeId(), c.getCaseTypeId());
                        },
                        () -> fail("Could not find case with id: " + actualCaseEntity.getId()));
    }

}
