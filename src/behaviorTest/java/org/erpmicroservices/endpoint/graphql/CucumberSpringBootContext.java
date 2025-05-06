package org.erpmicroservices.endpoint.graphql;

import io.cucumber.spring.CucumberContextConfiguration;
import org.erpmicroservices.peopleandorganizations.backend.repositories.*;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.PeopleAndOrganizationsEndpointGraphqlJavaApplication;
import org.erpmicroservices.peopleandorganizations.backend.repositories.CaseRepository;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@CucumberContextConfiguration
@AutoConfigureGraphQlTester
@ContextConfiguration(classes = PeopleAndOrganizationsEndpointGraphqlJavaApplication.class)
public class CucumberSpringBootContext {

    public CucumberSpringBootContext(CaseStatusTypeRepository caseStatusTypeRepository, CaseTypeRepository caseTypeRepository, CaseRepository caseRepository, PartyTypeRepository partyTypeRepository, PartyRepository partyRepository, CaseRoleTypeRepository caseRoleTypeRepository, CaseRoleRepository caseRoleRepository, ContactMechanismTypeRepository contactMechanismTypeRepository, PartyRoleTypeRepository partyRoleTypeRepository, PartyRoleRepository partyRoleRepository, CommunicationEventStatusTypeRepository communicationEventStatusTypeRepository, CommunicationEventTypeRepository communicationEventTypeRepository, PartyRelationshipTypeRepository partyRelationshipTypeRepository, PartyRelationshipStatusTypeRepository partyRelationshipStatusTypeRepository, PriorityTypeRepository priorityTypeRepository, PartyRelationshipRepository partyRelationshipRepository, CommunicationEventRepository communicationEventRepository, FacilityRepository facilityRepository, FacilityTypeRepository facilityTypeRepository, FacilityRoleTypeRepository facilityRoleTypeRepository, FacilityRoleRepository facilityRoleRepository, FacilityContactMechanismRepository facilityContactMechanismRepository, ContactMechanismRepository contactMechanismRepository, GeographicBoundaryRepository geographicBoundaryRepository, GeographicBoundaryTypeRepository geographicBoundaryTypeRepository, ContactMechanismGeographicBoundaryRepository contactMechanismGeographicBoundaryRepository, PartyContactMechanismRepository partyContactMechanismRepository, PartyContactMechanismPurposeRepository partyContactMechanismPurposeRepository, PartyContactMechanismPurposeTypeRepository partyContactMechanismPurposeTypeRepository, CommunicationEventPurposeTypeRepository communicationEventPurposeTypeRepository, CommunicationEventRoleTypeRepository communicationEventRoleTypeRepository, GraphQlTester graphQlTester) {
        this.caseStatusTypeRepository = caseStatusTypeRepository;
        this.caseTypeRepository = caseTypeRepository;
        this.caseRepository = caseRepository;
        this.partyTypeRepository = partyTypeRepository;
        this.partyRepository = partyRepository;
        this.caseRoleTypeRepository = caseRoleTypeRepository;
        this.caseRoleRepository = caseRoleRepository;
        this.contactMechanismTypeRepository = contactMechanismTypeRepository;
        this.partyRoleTypeRepository = partyRoleTypeRepository;
        this.partyRoleRepository = partyRoleRepository;
        this.communicationEventStatusTypeRepository = communicationEventStatusTypeRepository;
        this.communicationEventTypeRepository = communicationEventTypeRepository;
        this.partyRelationshipTypeRepository = partyRelationshipTypeRepository;
        this.partyRelationshipStatusTypeRepository = partyRelationshipStatusTypeRepository;
        this.priorityTypeRepository = priorityTypeRepository;
        this.partyRelationshipRepository = partyRelationshipRepository;
        this.communicationEventRepository = communicationEventRepository;
        this.facilityRepository = facilityRepository;
        this.facilityTypeRepository = facilityTypeRepository;
        this.facilityRoleTypeRepository = facilityRoleTypeRepository;
        this.facilityRoleRepository = facilityRoleRepository;
        this.facilityContactMechanismRepository = facilityContactMechanismRepository;
        this.contactMechanismRepository = contactMechanismRepository;
        this.geographicBoundaryRepository = geographicBoundaryRepository;
        this.geographicBoundaryTypeRepository = geographicBoundaryTypeRepository;
        this.contactMechanismGeographicBoundaryRepository = contactMechanismGeographicBoundaryRepository;
        this.partyContactMechanismRepository = partyContactMechanismRepository;
        this.partyContactMechanismPurposeRepository = partyContactMechanismPurposeRepository;
        this.partyContactMechanismPurposeTypeRepository = partyContactMechanismPurposeTypeRepository;
        this.communicationEventPurposeTypeRepository = communicationEventPurposeTypeRepository;
        this.communicationEventRoleTypeRepository = communicationEventRoleTypeRepository;
        this.graphQlTester = graphQlTester;
    }

    protected final CaseStatusTypeRepository caseStatusTypeRepository;

    protected final CaseTypeRepository caseTypeRepository;

    protected final CaseRepository caseRepository;

    protected final PartyTypeRepository partyTypeRepository;

    protected final PartyRepository partyRepository;

    protected final CaseRoleTypeRepository caseRoleTypeRepository;

    protected final CaseRoleRepository caseRoleRepository;

    protected final ContactMechanismTypeRepository contactMechanismTypeRepository;

    protected final PartyRoleTypeRepository partyRoleTypeRepository;

    protected final PartyRoleRepository partyRoleRepository;

    protected final CommunicationEventStatusTypeRepository communicationEventStatusTypeRepository;

    protected final CommunicationEventTypeRepository communicationEventTypeRepository;

    protected final PartyRelationshipTypeRepository partyRelationshipTypeRepository;

    protected final PartyRelationshipStatusTypeRepository partyRelationshipStatusTypeRepository;

    protected final PriorityTypeRepository priorityTypeRepository;

    protected final PartyRelationshipRepository partyRelationshipRepository;

    protected final CommunicationEventRepository communicationEventRepository;

    protected final FacilityRepository facilityRepository;

    protected final FacilityTypeRepository facilityTypeRepository;

    protected final FacilityRoleTypeRepository facilityRoleTypeRepository;

    protected final FacilityRoleRepository facilityRoleRepository;

    protected final FacilityContactMechanismRepository facilityContactMechanismRepository;

    protected final ContactMechanismRepository contactMechanismRepository;

    protected final GeographicBoundaryRepository geographicBoundaryRepository;

    protected final GeographicBoundaryTypeRepository geographicBoundaryTypeRepository;

    protected final ContactMechanismGeographicBoundaryRepository contactMechanismGeographicBoundaryRepository;

    protected final PartyContactMechanismRepository partyContactMechanismRepository;

    protected final PartyContactMechanismPurposeRepository partyContactMechanismPurposeRepository;

    protected final PartyContactMechanismPurposeTypeRepository partyContactMechanismPurposeTypeRepository;

    protected final CommunicationEventPurposeTypeRepository communicationEventPurposeTypeRepository;

    protected final CommunicationEventRoleTypeRepository communicationEventRoleTypeRepository;
    protected GraphQlTester.Response response;

    protected GraphQlTester graphQlTester;

    private final static DockerImageName DATABASE_IMAGE_NAME = DockerImageName
            .parse("erpmicroservices/people_and_organizations-database:latest")
            .asCompatibleSubstituteFor("postgres");
    protected static PostgreSQLContainer<?> postgresqlDbContainer = new PostgreSQLContainer<>(
            DATABASE_IMAGE_NAME
    );

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresqlDbContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresqlDbContainer::getUsername);
        registry.add("spring.datasource.password", postgresqlDbContainer::getPassword);
    }

    protected static Map<String, String> pageInfoSortingOn(final String sortBy) {
        return Map.of("pageNumber", "0"
                , "pageSize", "100"
                , "sortBy", sortBy
                , "sortDirection", "ASC");
    }
}
