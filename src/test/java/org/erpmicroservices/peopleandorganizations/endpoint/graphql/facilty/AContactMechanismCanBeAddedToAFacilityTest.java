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
	public void given() {
		super.given();
		aFacilityExists();
		aContactMechanismExists();
		localDate = LocalDate.now();
	}

	@Test
	@Override
	public void when() {
		response = graphQlTester.documentName("FacilityAddContactMechanism")
				           .operationName("AddFacilityContactMechanism")
				           .variable("newFacilityContactMechanism", Map.of(
						           "contactMechanismId", contactMechanismEntity.getId(),
						           "facilityId", facilityEntity.getId(),
						           "fromDate", localDate
				           ))
				           .execute();
	}

	@AfterEach
	@Override
	public void then() {
		response
				.path("addFacilityContactMechanism.id").hasValue()
				.path("addFacilityContactMechanism.fromDate").entity(LocalDate.class).isEqualTo(localDate)
				.path("addFacilityContactMechanism.contactMechanismEntity.id").entity(UUID.class).isEqualTo(contactMechanismEntity.getId())
				.path("addFacilityContactMechanism.contactMechanismEntity.endPoint").entity(String.class).isEqualTo(contactMechanismEntity.getEndPoint())
				.path("addFacilityContactMechanism.contactMechanismEntity.directions").entity(String.class).isEqualTo(contactMechanismEntity.getDirections())
				.path("addFacilityContactMechanism.contactMechanismEntity.contactMechanismTypeEntity.id").entity(UUID.class).isEqualTo(contactMechanismTypeEntity.getId())
				.path("addFacilityContactMechanism.contactMechanismEntity.contactMechanismTypeEntity.description").entity(String.class).isEqualTo(contactMechanismTypeEntity.getDescription());
	}
}
