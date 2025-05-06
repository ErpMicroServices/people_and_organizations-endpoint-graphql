package org.erpmicroservices.peopleandorganizations.endpoint.graphql.party;

import org.erpmicroservices.peopleandorganizations.backend.entities.PartyContactMechanismEntity;
import org.erpmicroservices.peopleandorganizations.backend.entities.PartyContactMechanismPurposeEntity;
import org.erpmicroservices.peopleandorganizations.backend.entities.PartyContactMechanismPurposeTypeEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

@SpringBootTest
@AutoConfigureGraphQlTester
public class AddPartyContactMechanismTest extends PartyGwtTemplate {

	private PartyContactMechanismEntity partyContactMechanismEntity;

	private PartyContactMechanismPurposeEntity partyContactMechanismPurposeEntity;

	private PartyContactMechanismPurposeTypeEntity partyContactMechanismPurposeTypeEntity;

	@BeforeEach
	@Override
	public void given() {
		super.given();
		aPartyExists();
		aGeographicBoundaryThatBelongsToAContactMechanismExists();
		aPartyContactMechanismPurposeExists();
		partyContactMechanismEntity = PartyContactMechanismEntity.builder()
				                        .comment("This is a comment")
				                        .contactMechanismId(contactMechanismEntity.getId())
				                        .doNotSolicitIndicator(true)
				                        .fromDate(LocalDate.now())
				                        .partyContactMechanismPurposeId(partyContactMechanismPurposeEntity.getId())
				                        .partyId(partyEntity.getId())
				                        .build();
	}

	@Test
	@Override
	public void when() {
		response = this.graphQlTester.documentName("PartyAddPartyContactMechanism")
				           .operationName("AddPartyContactMechanism")
				           .variable("partyContactMechanismNew", Map.of(
						           "partyId", partyContactMechanismEntity.getPartyId(),
						           "fromDate", partyContactMechanismEntity.getFromDate(),
						           "doNotSolicitIndicator", partyContactMechanismEntity.isDoNotSolicitIndicator(),
						           "comment", partyContactMechanismEntity.getComment(),
						           "contactMechanismId", partyContactMechanismEntity.getContactMechanismId(),
						           "purposeId", partyContactMechanismEntity.getPartyContactMechanismPurposeId()
				           ))
				           .variable("geographicBoundariesPageInfo", Map.of(
						           "pageNumber", 0,
						           "pageSize", 100,
						           "sortBy", "name",
						           "sortDirection", "ASC"
				           ))
				           .execute();
	}

	@AfterEach
	@Override
	public void then() {
		response.path("partyContactMechanismAdd.id").hasValue()
				.path("partyContactMechanismAdd.fromDate").entity(LocalDate.class).isEqualTo(partyContactMechanismEntity.getFromDate())
				.path("partyContactMechanismAdd.doNotSolicitIndicator").entity(Boolean.class).isEqualTo(partyContactMechanismEntity.isDoNotSolicitIndicator())
				.path("partyContactMechanismAdd.comment").entity(String.class).isEqualTo(partyContactMechanismEntity.getComment())
				.path("partyContactMechanismAdd.contactMechanismEntity.id").entity(UUID.class).isEqualTo(contactMechanismEntity.getId())
				.path("partyContactMechanismAdd.contactMechanismEntity.endPoint").entity(String.class).isEqualTo(contactMechanismEntity.getEndPoint())
				.path("partyContactMechanismAdd.contactMechanismEntity.directions").entity(String.class).isEqualTo(contactMechanismEntity.getDirections())
				.path("partyContactMechanismAdd.contactMechanismEntity.contactMechanismTypeEntity.id").entity(UUID.class).isEqualTo(contactMechanismTypeEntity.getId())
				.path("partyContactMechanismAdd.contactMechanismEntity.contactMechanismTypeEntity.description").entity(String.class).isEqualTo(contactMechanismTypeEntity.getDescription());
	}


	private void aPartyContactMechanismPurposeExists() {
		if (partyContactMechanismPurposeTypeEntity == null) {
			aPartyContactMechanismPurposeTypeExists();
		}
		partyContactMechanismPurposeEntity = partyContactMechanismPurposeRepository.save(PartyContactMechanismPurposeEntity.builder()
				                                                                           .contactMechanismPurposeTypeId(partyContactMechanismPurposeTypeEntity.getId())
				                                                                           .fromDate(LocalDate.now())
				                                                                           .build());
	}

	private void aPartyContactMechanismPurposeTypeExists() {
		partyContactMechanismPurposeTypeEntity = partyContactMechanismPurposeTypeRepository.save(PartyContactMechanismPurposeTypeEntity.builder()
				                                                                                   .description("PartyEntity Contact Mechanism Purpose Type")
				                                                                                   .build());
	}


}
