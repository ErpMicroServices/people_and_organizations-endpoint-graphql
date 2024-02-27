package org.erpmicroservices.endpoint.graphql.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.erpmicroservices.endpoint.graphql.CucumberSpringBootContext;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.repositories.*;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.graphql.CaseEdge;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.models.Case;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.models.CaseStatusType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.models.CaseType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.repositories.*;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.contactmechanism.PartyContactMechanismPurposeRepository;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.*;
import org.springframework.graphql.test.tester.GraphQlTester;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CaseSteps extends CucumberSpringBootContext {
    private final List<Case> expectedCases = new ArrayList<>();
    private List<CaseEdge> response;

    private final String casesGraphQlPath = "cases.edges";

    public CaseSteps(CaseStatusTypeRepository caseStatusTypeRepository, CaseTypeRepository caseTypeRepository, CaseRepository caseRepository, PartyTypeRepository partyTypeRepository, PartyRepository partyRepository, CaseRoleTypeRepository caseRoleTypeRepository, CaseRoleRepository caseRoleRepository, ContactMechanismTypeRepository contactMechanismTypeRepository, PartyRoleTypeRepository partyRoleTypeRepository, PartyRoleRepository partyRoleRepository, CommunicationEventStatusTypeRepository communicationEventStatusTypeRepository, CommunicationEventTypeRepository communicationEventTypeRepository, PartyRelationshipTypeRepository partyRelationshipTypeRepository, PartyRelationshipStatusTypeRepository partyRelationshipStatusTypeRepository, PriorityTypeRepository priorityTypeRepository, PartyRelationshipRepository partyRelationshipRepository, CommunicationEventRepository communicationEventRepository, FacilityRepository facilityRepository, FacilityTypeRepository facilityTypeRepository, FacilityRoleTypeRepository facilityRoleTypeRepository, FacilityRoleRepository facilityRoleRepository, FacilityContactMechanismRepository facilityContactMechanismRepository, ContactMechanismRepository contactMechanismRepository, GeographicBoundaryRepository geographicBoundaryRepository, GeographicBoundaryTypeRepository geographicBoundaryTypeRepository, ContactMechanismGeographicBoundaryRepository contactMechanismGeographicBoundaryRepository, PartyContactMechanismRepository partyContactMechanismRepository, PartyContactMechanismPurposeRepository partyContactMechanismPurposeRepository, PartyContactMechanismPurposeTypeRepository partyContactMechanismPurposeTypeRepository, CommunicationEventPurposeTypeRepository communicationEventPurposeTypeRepository, CommunicationEventRoleTypeRepository communicationEventRoleTypeRepository, GraphQlTester graphQlTester) {
        super(caseStatusTypeRepository, caseTypeRepository, caseRepository, partyTypeRepository, partyRepository, caseRoleTypeRepository, caseRoleRepository, contactMechanismTypeRepository, partyRoleTypeRepository, partyRoleRepository, communicationEventStatusTypeRepository, communicationEventTypeRepository, partyRelationshipTypeRepository, partyRelationshipStatusTypeRepository, priorityTypeRepository, partyRelationshipRepository, communicationEventRepository, facilityRepository, facilityTypeRepository, facilityRoleTypeRepository, facilityRoleRepository, facilityContactMechanismRepository, contactMechanismRepository, geographicBoundaryRepository, geographicBoundaryTypeRepository, contactMechanismGeographicBoundaryRepository, partyContactMechanismRepository, partyContactMechanismPurposeRepository, partyContactMechanismPurposeTypeRepository, communicationEventPurposeTypeRepository, communicationEventRoleTypeRepository, graphQlTester);
    }

    @Given("there are {int} cases with a type of {string} with a status of {string} in the database")
    public void there_are_cases_with_a_type_of_with_a_status_of_in_the_database(Integer numberOfCases, String caseTypeDescription, String caseStatusDescription) {
        final CaseType caseType = caseTypeRepository.findByDescription(caseTypeDescription);
        final CaseStatusType caseStatusType = caseStatusTypeRepository.findByDescription(caseStatusDescription);

        for (int i = 0; i < numberOfCases; i++) {
            expectedCases.add(caseRepository.save(Case.builder()
                    .caseStatusTypeId(caseStatusType.getId())
                    .caseTypeId(caseType.getId())
                    .description("Case number " + i + " of " + numberOfCases)
                    .startedAt(ZonedDateTime.now())
                    .build()));
        }
    }

    @When("I search for all cases")
    public void i_search_for_all_cases() {
        response = this.graphQlTester.documentName("caseQuery")
                .operationName("caseQuery")
                .variable("rolesPageInfo", pageInfoSortingOn("fromDate"))
                .variable("communicationEventPageInfo", pageInfoSortingOn("started"))
                .variable("casePageInfo", pageInfoSortingOn("description"))
                .execute()
                .path(casesGraphQlPath)
                .entityList(CaseEdge.class)
                .get();

    }

    @Then("I get {int} cases")
    public void i_get_cases(Integer expectedSize) {
        assertEquals(expectedSize, response.size());

    }

    @Then("{int} of them are cases of type {string}")
    public void of_them_are_cases_of_type(Integer expectedNumberOfCases, String caseTypeDescription) {
//        final CaseType expectedCasetype = caseTypeRepository.findByDescription(caseTypeDescription);
        assertEquals(expectedNumberOfCases, response.stream()
                .filter(caseEdge ->
                        caseEdge.getNode().getCaseType().getDescription().equals(caseTypeDescription))
                .toList()
                .size());
    }
}
