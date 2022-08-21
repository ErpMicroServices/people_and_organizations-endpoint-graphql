package org.erpmicroservices.peopleandorganizations.endpoint.graphql.party;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.contactmechanism.ContactMechanism;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.contactmechanism.PartyContactMechanism;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.contactmechanism.PartyContactMechanismPurpose;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.contactmechanism.PartyContactMechanismPurposeType;
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
public class AddPartyContactMechanism extends PartyGwtTemplate {

	private PartyContactMechanism partyContactMechanism;

	private ContactMechanism contactMechanism;

	private PartyContactMechanismPurpose partyContactMechanismPurpose;

	private PartyContactMechanismPurposeType partyContactMechanismPurposeType;

	@BeforeEach
	@Override
	public void given() {
		super.given();
		aPartyExists();
		aContactMechanismExists();
		aPartyContactMechanismPurposeExists();
		partyContactMechanism = PartyContactMechanism.builder()
				                        .comment("This is a comment")
				                        .contactMechanismId(contactMechanism.getId())
				                        .doNotSolicitIndicator(true)
				                        .fromDate(LocalDate.now())
				                        .partyContactMechanismPurposeId(partyContactMechanismPurpose.getId())
				                        .partyId(party.getId())
				                        .build();
	}

	@Test
	@Override
	public void when() {
		response = this.graphQlTester.documentName("PartyAddPartyContactMechanism")
				           .operationName("AddPartyContactMechanism")
				           .variable("partyContactMechanismNew", Map.of(
						           "partyId", partyContactMechanism.getPartyId(),
						           "fromDate", partyContactMechanism.getFromDate(),
						           "doNotSolicitIndicator", partyContactMechanism.isDoNotSolicitIndicator(),
						           "comment", partyContactMechanism.getComment(),
						           "contactMechanismId", partyContactMechanism.getContactMechanismId(),
						           "purposeId", partyContactMechanism.getPartyContactMechanismPurposeId()
				           ))
				           .variable("geographicBoundariesPageInfo", Map.of(
						           "pageNumber", 0,
						           "pageSize", 100,
						           "sortBy", "name",
						           "sortDirection", "DESC"
				           ))
				           .execute();
	}

	@AfterEach
	@Override
	public void then() {
		response.path("partyContactMechanismAdd.id").hasValue()
				.path("partyContactMechanismAdd.fromDate").entity(LocalDate.class).isEqualTo(partyContactMechanism.getFromDate())
				.path("partyContactMechanismAdd.doNotSolicitIndicator").entity(Boolean.class).isEqualTo(partyContactMechanism.isDoNotSolicitIndicator())
				.path("partyContactMechanismAdd.comment").entity(String.class).isEqualTo(partyContactMechanism.getComment())
				.path("partyContactMechanismAdd.contactMechanism.id").entity(UUID.class).isEqualTo(contactMechanism.getId())
				.path("partyContactMechanismAdd.contactMechanism.endPoint").entity(String.class).isEqualTo(contactMechanism.getEndPoint())
				.path("partyContactMechanismAdd.contactMechanism.directions").entity(String.class).isEqualTo(contactMechanism.getDirections())
				.path("partyContactMechanismAdd.contactMechanism.contactMechanismType.id").entity(UUID.class).isEqualTo(contactMechanismType.getId())
				.path("partyContactMechanismAdd.contactMechanism.contactMechanismType.description").entity(String.class).isEqualTo(contactMechanismType.getDescription());
	}


	private void aPartyContactMechanismPurposeExists() {
		if (partyContactMechanismPurposeType == null) {
			aPartyContactMechanismPurposeTypeExists();
		}
		partyContactMechanismPurpose = partyContactMechanismPurposeRepository.save(PartyContactMechanismPurpose.builder()
				                                                                           .contactMechanismPurposeTypeId(partyContactMechanismPurposeType.getId())
				                                                                           .fromDate(LocalDate.now())
				                                                                           .build());
	}

	private void aPartyContactMechanismPurposeTypeExists() {
		partyContactMechanismPurposeType = partyContactMechanismPurposeTypeRepository.save(PartyContactMechanismPurposeType.builder()
				                                                                                   .description("Party Contact Mechanism Purpose Type")
				                                                                                   .build());
	}

	private void aContactMechanismExists() {
		if (contactMechanismType == null) {
			aContactMechanismTypeExists();
		}
		contactMechanism = contactMechanismRepository.save(ContactMechanism.builder()
				                                                   .contactMechanismTypeId(contactMechanismType.getId())
				                                                   .directions("These are directions")
				                                                   .endPoint("This is an endpoing")
				                                                   .build());
	}

}
