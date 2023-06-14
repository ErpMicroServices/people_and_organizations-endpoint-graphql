package org.erpmicroservices.peopleandorganizations.endpoint.graphql.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.CucumberSpringBootContext;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.Case;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.CaseStatusType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.CaseType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.CaseRepository;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.CaseStatusTypeRepository;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.CaseTypeRepository;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.graphql.test.tester.GraphQlTester;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureGraphQlTester
public class CaseSteps extends CucumberSpringBootContext {
    private final CaseRepository caseRepository;
    private final CaseTypeRepository caseTypeRepository;
    private final CaseStatusTypeRepository caseStatusTypeRepository;

    private final List<Case> expectedCases = new ArrayList<>();
    private List<Case> actualCases;

    public CaseSteps(CaseRepository caseRepository, CaseTypeRepository caseTypeRepository, CaseStatusTypeRepository caseStatusTypeRepository, GraphQlTester graphQlTester) {
//        super(graphQlTester);
        this.caseRepository = caseRepository;
        this.caseTypeRepository = caseTypeRepository;
        this.caseStatusTypeRepository = caseStatusTypeRepository;
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
                .execute();
    }

    @Then("I get {int} cases")
    public void i_get_cases(Integer expectedSize) {
        assertEquals(expectedCases.size(), actualCases.size());
    }

    @Then("{int} of them are cases of type {string}")
    public void of_them_are_cases_of_type(Integer expectedNumberOfCases, String caseTypeDescription) {
        final CaseType expectedCasetype = caseTypeRepository.findByDescription(caseTypeDescription);
        final List<Case> actualCasesOfCaseType = actualCases.stream().filter(actualCase -> expectedCasetype.getId().equals(actualCase.getCaseTypeId())).toList();
        assertEquals(expectedNumberOfCases, actualCasesOfCaseType.size());

    }
}
