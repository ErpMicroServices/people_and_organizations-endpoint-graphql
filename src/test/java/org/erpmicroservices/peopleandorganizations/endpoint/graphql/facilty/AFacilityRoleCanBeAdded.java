package org.erpmicroservices.peopleandorganizations.endpoint.graphql.facilty;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.facility.FacilityRole;
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
public class AFacilityRoleCanBeAdded extends FacilityGwtTemplate {

	@BeforeEach
	@Override
	public void given() {
		super.given();
		aPartyExists();
		aFacilityExists();
		aFacilityRoleTypeExists();
		facilityRole = FacilityRole.builder()
				               .facilityRoleTypeId(facilityRoleType.getId())
				               .facilityId(facility.getId())
				               .partyId(party.getId())
				               .fromDate(LocalDate.now())
				               .build();

	}

	@Test
	@Override
	public void when() {
		response = graphQlTester.documentName("FacilityAddFacilityRole")
				           .operationName("AddFacilityRole")
				           .variable("newFacilityRole", Map.of(
						           "facilityRoleTypeId", facilityRole.getFacilityRoleTypeId(),
						           "partyId", facilityRole.getPartyId(),
						           "facilityId", facilityRole.getFacilityId(),
						           "fromDate", facilityRole.getFromDate()
				           ))
				           .execute();
	}

	@AfterEach
	@Override
	public void then() {
		response
				.path("addFacilityRole.id").hasValue()
				.path("addFacilityRole.fromDate").entity(LocalDate.class).isEqualTo(facilityRole.getFromDate())
				.path("addFacilityRole.facility.id").entity(UUID.class).isEqualTo(facility.getId())
				.path("addFacilityRole.facility.description").entity(String.class).isEqualTo(facility.getDescription())
				.path("addFacilityRole.facility.squareFootage").entity(Long.class).isEqualTo(facility.getSquareFootage())
				.path("addFacilityRole.facility.facilityType.id").entity(UUID.class).isEqualTo(facilityType.getId())
				.path("addFacilityRole.facility.facilityType.description").entity(String.class).isEqualTo(facilityType.getDescription())
				.path("addFacilityRole.facilityRoleType.id").entity(UUID.class).isEqualTo(facilityRoleType.getId())
				.path("addFacilityRole.facilityRoleType.description").entity(String.class).isEqualTo(facilityRoleType.getDescription())
				.path("addFacilityRole.party.id").entity(UUID.class).isEqualTo(party.getId())
				.path("addFacilityRole.party.partyType.id").entity(UUID.class).isEqualTo(partyType.getId())
				.path("addFacilityRole.party.partyType.description").entity(String.class).isEqualTo(partyType.getDescription());
	}
}
