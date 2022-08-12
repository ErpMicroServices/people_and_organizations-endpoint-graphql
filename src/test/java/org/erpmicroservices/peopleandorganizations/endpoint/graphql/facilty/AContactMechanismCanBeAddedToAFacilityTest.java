package org.erpmicroservices.peopleandorganizations.endpoint.graphql.facilty;

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
public class AContactMechanismCanBeAddedToAFacilityTest extends FacilityGwtTemplate {

	private LocalDate localDate;

	@BeforeEach
	@Override
	public void givenTheFollowing() {
		super.givenTheFollowing();
		aFacilityExists();
		aContactMechanismExists();
		localDate = LocalDate.now();
	}

	@Test
	@Override
	public void whenThisHappens() {
		response = graphQlTester.documentName("FacilityAddContactMechanism")
				           .operationName("AddFacilityContactMechanism")
				           .variable("newFacilityContactMechanism", Map.of(
						           "contactMechanismId", contactMechanism.getId(),
						           "facilityId", facility.getId(),
						           "fromDate", localDate
				           ))
				           .execute();
	}

	@AfterEach
	@Override
	public void thenThisIsExpected() {
		response
				.path("addFacilityContactMechanism.id").hasValue()
				.path("addFacilityContactMechanism.fromDate").entity(LocalDate.class).isEqualTo(localDate)
				.path("addFacilityContactMechanism.contactMechanism.id").entity(UUID.class).isEqualTo(contactMechanism.getId())
				.path("addFacilityContactMechanism.contactMechanism.endPoint").entity(String.class).isEqualTo(contactMechanism.getEndPoint())
				.path("addFacilityContactMechanism.contactMechanism.directions").entity(String.class).isEqualTo(contactMechanism.getDirections())
				.path("addFacilityContactMechanism.contactMechanism.contactMechanismType.id").entity(UUID.class).isEqualTo(contactMechanismType.getId())
				.path("addFacilityContactMechanism.contactMechanism.contactMechanismType.description").entity(String.class).isEqualTo(contactMechanismType.getDescription());
	}
}
