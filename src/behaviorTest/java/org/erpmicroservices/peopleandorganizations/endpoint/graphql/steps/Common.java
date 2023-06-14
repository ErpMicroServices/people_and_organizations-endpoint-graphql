package org.erpmicroservices.peopleandorganizations.endpoint.graphql.steps;

import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.CucumberSpringBootContext;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.CommunicationEventStatusType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.CaseStatusType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.CaseType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.contactmechanism.PartyContactMechanismPurposeRepository;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.role.PartyRoleType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.type.PartyType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.*;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.data.domain.Pageable;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.testcontainers.utility.DockerImageName;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

@AutoConfigureGraphQlTester
public class Common extends CucumberSpringBootContext {

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
    private final List<CaseType> caseTypes = new ArrayList<>();

    private final static DockerImageName DATABASE_IMAGE_NAME = DockerImageName.parse("erpmicroservices/people_and_organizations-database:latest");

//    private final static GenericContainer postgresqlDbContainer = new GenericContainer(DATABASE_IMAGE_NAME)
//            .withExposedPorts(5432)
//            .waitingFor(Wait.forLogMessage(".*database system is ready to accept connections.*\\n",1));

    public Common(CaseTypeRepository caseTypeRepository, CaseStatusTypeRepository caseStatusTypeRepository, PartyContactMechanismRepository partyContactMechanismRepository, ContactMechanismRepository contactMechanismRepository, ContactMechanismGeographicBoundaryRepository contactMechanismGeographicBoundaryRepository, CommunicationEventPurposeTypeRepository communicationEventPurposeTypeRepository, CaseRepository caseRepository, PartyTypeRepository partyTypeRepository, PartyRelationshipStatusTypeRepository partyRelationshipStatusTypeRepository, FacilityRoleRepository facilityRoleRepository, PartyRepository partyRepository, CaseRoleTypeRepository caseRoleTypeRepository, PartyRelationshipTypeRepository partyRelationshipTypeRepository, CommunicationEventRepository communicationEventRepository, FacilityRoleTypeRepository facilityRoleTypeRepository, GeographicBoundaryRepository geographicBoundaryRepository, CommunicationEventTypeRepository communicationEventTypeRepository, CaseRoleRepository caseRoleRepository, PartyContactMechanismPurposeRepository partyContactMechanismPurposeRepository, ContactMechanismTypeRepository contactMechanismTypeRepository, FacilityContactMechanismRepository facilityContactMechanismRepository, PartyRoleTypeRepository partyRoleTypeRepository, PartyRoleRepository partyRoleRepository, FacilityTypeRepository facilityTypeRepository, GeographicBoundaryTypeRepository geographicBoundaryTypeRepository, CommunicationEventStatusTypeRepository communicationEventStatusTypeRepository, PartyRelationshipRepository partyRelationshipRepository, PartyContactMechanismPurposeTypeRepository partyContactMechanismPurposeTypeRepository, CommunicationEventRoleTypeRepository communicationEventRoleTypeRepository, PriorityTypeRepository priorityTypeRepository, FacilityRepository facilityRepository, GraphQlTester graphQlTester) {
//        super(graphQlTester);
        this.caseTypeRepository = caseTypeRepository;
        this.caseStatusTypeRepository = caseStatusTypeRepository;
        this.partyContactMechanismRepository = partyContactMechanismRepository;
        this.contactMechanismRepository = contactMechanismRepository;
        this.contactMechanismGeographicBoundaryRepository = contactMechanismGeographicBoundaryRepository;
        this.communicationEventPurposeTypeRepository = communicationEventPurposeTypeRepository;
        this.caseRepository = caseRepository;
        this.partyTypeRepository = partyTypeRepository;
        this.partyRelationshipStatusTypeRepository = partyRelationshipStatusTypeRepository;
        this.facilityRoleRepository = facilityRoleRepository;
        this.partyRepository = partyRepository;
        this.caseRoleTypeRepository = caseRoleTypeRepository;
        this.partyRelationshipTypeRepository = partyRelationshipTypeRepository;
        this.communicationEventRepository = communicationEventRepository;
        this.facilityRoleTypeRepository = facilityRoleTypeRepository;
        this.geographicBoundaryRepository = geographicBoundaryRepository;
        this.communicationEventTypeRepository = communicationEventTypeRepository;
        this.caseRoleRepository = caseRoleRepository;
        this.partyContactMechanismPurposeRepository = partyContactMechanismPurposeRepository;
        this.contactMechanismTypeRepository = contactMechanismTypeRepository;
        this.facilityContactMechanismRepository = facilityContactMechanismRepository;
        this.partyRoleTypeRepository = partyRoleTypeRepository;
        this.partyRoleRepository = partyRoleRepository;
        this.facilityTypeRepository = facilityTypeRepository;
        this.geographicBoundaryTypeRepository = geographicBoundaryTypeRepository;
        this.communicationEventStatusTypeRepository = communicationEventStatusTypeRepository;
        this.partyRelationshipRepository = partyRelationshipRepository;
        this.partyContactMechanismPurposeTypeRepository = partyContactMechanismPurposeTypeRepository;
        this.communicationEventRoleTypeRepository = communicationEventRoleTypeRepository;
        this.priorityTypeRepository = priorityTypeRepository;
        this.facilityRepository = facilityRepository;
    }

    @BeforeAll
    public static void setupWorld() {
//        System.out.println("######################################## Starting the db docker instance ##################");
//        postgresqlDbContainer.start();

//        System.out.println("############################     " + postgresqlDbContainer.getContainerName() + "     ######################");
    }

    @Before
    public void setupTheScenario() {
        theDatabaseIsEmpty();
    }

    @Given("the following types:")
    public void the_following_types(io.cucumber.datatable.DataTable dataTable) {
        final List<List<String>> dataTableLists = dataTable.asLists();
        dataTableLists.stream()
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

        partyTypeRepository.findPartyTypesByParentIdIsNull(Pageable.unpaged()).forEach(root -> deletePartyTypeChildren(root));
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
        partyTypeRepository.findPartyTypesByParentId(root.getId(), Pageable.unpaged()).forEach(child -> deletePartyTypeChildren(child));
        partyTypeRepository.delete(root);
    }

    private void deleteCommunicationEventStatusTypeChildren(CommunicationEventStatusType root) {
        communicationEventStatusTypeRepository.findCommunicationEventStatusTypeByParentId(root.getParentId(), Pageable.unpaged()).stream()
                .forEach(child -> deleteCommunicationEventStatusTypeChildren(child));
        communicationEventStatusTypeRepository.delete(root);
    }

    private void deletePartyRoleTypeChildren(PartyRoleType root) {
        partyRoleTypeRepository.findPartyRoleTypesByParentId(root.getId(), Pageable.unpaged()).stream()
                .forEach(partyRoleType -> deletePartyRoleTypeChildren(partyRoleType));
        partyRoleTypeRepository.delete(root);
    }
}
