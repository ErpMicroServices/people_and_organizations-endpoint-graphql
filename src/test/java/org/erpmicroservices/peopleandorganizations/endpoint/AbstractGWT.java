package org.erpmicroservices.peopleandorganizations.endpoint;

import jakarta.validation.constraints.NotNull;
import org.erpmicroservices.peopleandorganizations.backend.entities.*;
import org.erpmicroservices.peopleandorganizations.backend.repositories.*;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.models.CaseRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.graphql.test.tester.GraphQlTester;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.erpmicroservices.peopleandorganizations.endpoint.builders.ContactMechanismTypeDataBuilder.completeContactMechanismType;
import static org.erpmicroservices.peopleandorganizations.endpoint.builders.PartyRoleTestDataBuilder.completePartyRole;
import static org.erpmicroservices.peopleandorganizations.endpoint.builders.PartyRoleTypeDataBuilder.completePartyRoleType;
import static org.erpmicroservices.peopleandorganizations.endpoint.builders.PartyTestDataBuilder.completeParty;
import static org.erpmicroservices.peopleandorganizations.endpoint.builders.PartyTypeTestDataBuilder.completePartyType;

abstract public class AbstractGWT {
	protected GraphQlTester.Response response;
	@Autowired
	protected GraphQlTester graphQlTester;
	@Autowired
	protected CaseStatusTypeRepository caseStatusTypeRepository;
	@Autowired
	protected CaseTypeRepository caseTypeRepository;
	@Autowired
	protected CaseRepository caseRepository;
	@Autowired
	protected PartyTypeRepository partyTypeRepository;
	@Autowired
	protected PartyRepository partyRepository;
	@Autowired
	protected CaseRoleTypeRepository caseRoleTypeRepository;
	@Autowired
	protected CaseRoleRepository caseRoleRepository;
	@Autowired
	protected ContactMechanismTypeRepository contactMechanismTypeRepository;
	@Autowired
	protected PartyRoleTypeRepository partyRoleTypeRepository;
	@Autowired
	protected PartyRoleRepository partyRoleRepository;
	@Autowired
	protected CommunicationEventStatusTypeRepository communicationEventStatusTypeRepository;
	@Autowired
	protected CommunicationEventTypeRepository communicationEventTypeRepository;
	@Autowired
	protected PartyRelationshipTypeRepository partyRelationshipTypeRepository;
	@Autowired
	protected PartyRelationshipStatusTypeRepository partyRelationshipStatusTypeRepository;
	@Autowired
	protected PriorityTypeRepository priorityTypeRepository;
	@Autowired
	protected PartyRelationshipRepository partyRelationshipRepository;
	@Autowired
	protected CommunicationEventRepository communicationEventRepository;
	@Autowired
	protected FacilityRepository facilityRepository;
	@Autowired
	protected FacilityTypeRepository facilityTypeRepository;
	@Autowired
	protected FacilityRoleTypeRepository facilityRoleTypeRepository;
	@Autowired
	protected FacilityRoleRepository facilityRoleRepository;
	@Autowired
	protected FacilityContactMechanismRepository facilityContactMechanismRepository;
	protected PartyTypeEntity partyTypeEntity;
	@Autowired
	protected ContactMechanismRepository contactMechanismRepository;

	@Autowired
	protected GeographicBoundaryRepository geographicBoundaryRepository;

	@Autowired
	protected GeographicBoundaryTypeRepository geographicBoundaryTypeRepository;

	@Autowired
	protected ContactMechanismGeographicBoundaryRepository contactMechanismGeographicBoundaryRepository;

	@Autowired
	protected PartyContactMechanismRepository partyContactMechanismRepository;

	@Autowired
	protected PartyContactMechanismPurposeRepository partyContactMechanismPurposeRepository;

	@Autowired
	protected PartyContactMechanismPurposeTypeRepository partyContactMechanismPurposeTypeRepository;

	@Autowired
	protected CommunicationEventPurposeTypeRepository communicationEventPurposeTypeRepository;

	@Autowired
	protected CommunicationEventRoleTypeRepository communicationEventRoleTypeRepository;

	protected PartyEntity partyEntity;

	protected ContactMechanismTypeEntity contactMechanismTypeEntity;
	protected PartyRoleEntity partyRoleEntity;
	protected CommunicationEventStatusTypeEntity communicationEventStatusTypeEntity;
	protected CommunicationEventTypeEntity communicationEventTypeEntity;
	protected CommunicationEventEntity communicationEventEntity;
	protected PartyRoleTypeEntity partyRoleTypeEntity;
	protected PartyRelationshipEntity partyRelationshipEntity;
	protected PartyRelationshipTypeEntity partyRelationshipTypeEntity;
	protected PartyRelationshipStatusTypeEntity partyRelationshipStatusTypeEntity;
	protected PriorityTypeEntity priorityTypeEntity;
	protected ContactMechanismEntity contactMechanismEntity;
	protected GeographicBoundaryEntity geographicBoundaryEntity;
	protected GeographicBoundaryTypeEntity geographicBoundaryTypeEntity;
	protected ContactMechanismGeographicBoundaryEntity contactMechanismGeographicBoundaryEntity;

	protected CommunicationEventPurposeTypeEntity communicationEventPurposeTypeEntity;

	@BeforeEach
	public void given() {
		theDatabaseIsEmpty();
	}


	@Test
	abstract public void when();

	@AfterEach
	abstract public void then();

	@NotNull
	protected static Map<String, String> pageInfoSortingOn(final String sortBy) {
		return Map.of("pageNumber", "0"
				, "pageSize", "100"
				, "sortBy", sortBy
				, "sortDirection", "ASC");
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

		final List<PartyTypeEntity> list = partyTypeRepository.findPartyTypesByParentIdIsNotNull().stream().map(partyType1 -> {
			partyType1.setParentId(null);
			return partyType1;
		}).toList();
		partyTypeRepository.saveAll(list);
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

	private void deleteCommunicationEventStatusTypeChildren(final CommunicationEventStatusTypeEntity parent) {
		communicationEventStatusTypeRepository.findCommunicationEventStatusTypeByParentId(parent.getId(), Pageable.unpaged()).forEach(this::deleteCommunicationEventStatusTypeChildren);
		communicationEventStatusTypeRepository.delete(parent);
	}

	private void deletePartyRoleTypeChildren(final PartyRoleTypeEntity parent) {
		partyRoleTypeRepository.findPartyRoleTypesByParentId(parent.getId(), Pageable.unpaged()).forEach(this::deletePartyRoleTypeChildren);
		partyRoleTypeRepository.delete(parent);
	}

	protected PartyEntity aPartyExists() {
		if (partyTypeEntity == null) {
			aPartyTypeExists();
		}
		partyEntity = partyRepository.save(completeParty()
				                             .partyTypeId(partyTypeEntity.getId())
				                             .build());
		return partyEntity;
	}

	protected PartyTypeEntity aPartyTypeExists() {
		partyTypeEntity = partyTypeRepository.save(completePartyType().build());
		return partyTypeEntity;
	}

	protected PartyRoleEntity aPartyRoleExists(final PartyEntity partyEntity) {
		if (partyRoleTypeEntity == null) {
			aPartyRoleTypeExists();
		}
		partyRoleEntity = partyRoleRepository.save(completePartyRole()
				                                     .partyId(partyEntity.getId())
				                                     .partyRoleTypeId(partyRoleTypeEntity.getId())
				                                     .build());
		return partyRoleEntity;
	}

	protected PartyRoleTypeEntity aPartyRoleTypeExists() {
		partyRoleTypeEntity = partyRoleTypeRepository.save(completePartyRoleType().build());
		return partyRoleTypeEntity;
	}

	protected ContactMechanismTypeEntity aContactMechanismTypeExists() {
		contactMechanismTypeEntity = contactMechanismTypeRepository.save(completeContactMechanismType().build());
		return contactMechanismTypeEntity;
	}

	protected ContactMechanismEntity aContactMechanismExists() {
		if (contactMechanismTypeEntity == null) {
			aContactMechanismTypeExists();
		}
		contactMechanismEntity = contactMechanismRepository.save(ContactMechanismEntity.builder()
				                                                   .endPoint("ContactMechanismEntity Test Data endPoint " + UUID.randomUUID())
				                                                   .directions("ContactMechanismEntity Test Data directions " + UUID.randomUUID())
				                                                   .contactMechanismTypeId(contactMechanismTypeEntity.getId())
				                                                   .build());
		return contactMechanismEntity;
	}

	protected ContactMechanismGeographicBoundaryEntity aGeographicBoundaryThatBelongsToAContactMechanismExists() {
		if (contactMechanismEntity == null) {
			aContactMechanismExists();
		}
		if (geographicBoundaryTypeEntity == null) {
			aGeographicBoundaryTypeExists();
		}
		if (geographicBoundaryEntity == null) {
			aGeographicBoundaryExists();
		}
		contactMechanismGeographicBoundaryEntity = contactMechanismGeographicBoundaryRepository.save(ContactMechanismGeographicBoundaryEntity.builder()
				                                                                                       .geographicBoundaryId(geographicBoundaryEntity.getId())
				                                                                                       .contactMechanismId(contactMechanismEntity.getId())
				                                                                                       .build());
		return contactMechanismGeographicBoundaryEntity;
	}

	protected GeographicBoundaryEntity aGeographicBoundaryExists() {
		if (geographicBoundaryTypeEntity == null) {
			aGeographicBoundaryTypeExists();
		}
		geographicBoundaryEntity = geographicBoundaryRepository.save(GeographicBoundaryEntity.builder()
				                                                       .abbreviation("abbreviation test data " + UUID.randomUUID())
				                                                       .geoCode("geocode test data " + UUID.randomUUID())
				                                                       .geographicBoundaryTypeId(geographicBoundaryTypeEntity.getId())
				                                                       .name("name test data " + UUID.randomUUID())
				                                                       .build());
		return geographicBoundaryEntity;
	}

	protected GeographicBoundaryTypeEntity aGeographicBoundaryTypeExists() {
		geographicBoundaryTypeEntity = geographicBoundaryTypeRepository.save(GeographicBoundaryTypeEntity.builder()
				                                                               .description("geographicBoundaryTypeEntity test data " + UUID.randomUUID())
				                                                               .build());
		return geographicBoundaryTypeEntity;
	}
}
