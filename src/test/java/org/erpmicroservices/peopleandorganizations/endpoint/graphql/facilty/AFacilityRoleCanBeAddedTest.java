package org.erpmicroservices.peopleandorganizations.endpoint.graphql.facilty;

import org.erpmicroservices.peopleandorganizations.backend.entities.FacilityRoleEntity;
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
public class AFacilityRoleCanBeAddedTest extends FacilityGwtTemplate {

	@BeforeEach
	@Override
	public void given() {
		super.given();
		aPartyExists();
		aFacilityExists();
		aFacilityRoleTypeExists();
		facilityRoleEntity = FacilityRoleEntity.builder()
				               .facilityRoleTypeId(facilityRoleTypeEntity.getId())
				               .facilityId(facilityEntity.getId())
				               .partyId(partyEntity.getId())
				               .fromDate(LocalDate.now())
				               .build();

	}

	@Test
	@Override
	public void when() {
		response = graphQlTester.documentName("FacilityAddFacilityRole")
				           .operationName("AddFacilityRole")
				           .variable("newFacilityRole", Map.of(
						           "facilityRoleTypeId", facilityRoleEntity.getFacilityRoleTypeId(),
						           "partyId", facilityRoleEntity.getPartyId(),
						           "facilityId", facilityRoleEntity.getFacilityId(),
						           "fromDate", facilityRoleEntity.getFromDate()
				           ))
				           .execute();
	}

	@AfterEach
	@Override
	public void then() {
		response
				.path("addFacilityRole.id").hasValue()
				.path("addFacilityRole.fromDate").entity(LocalDate.class).isEqualTo(facilityRoleEntity.getFromDate())
				.path("addFacilityRole.facilityEntity.id").entity(UUID.class).isEqualTo(facilityEntity.getId())
				.path("addFacilityRole.facilityEntity.description").entity(String.class).isEqualTo(facilityEntity.getDescription())
				.path("addFacilityRole.facilityEntity.squareFootage").entity(Long.class).isEqualTo(facilityEntity.getSquareFootage())
				.path("addFacilityRole.facilityEntity.facilityTypeEntity.id").entity(UUID.class).isEqualTo(facilityTypeEntity.getId())
				.path("addFacilityRole.facilityEntity.facilityTypeEntity.description").entity(String.class).isEqualTo(facilityTypeEntity.getDescription())
				.path("addFacilityRole.facilityRoleTypeEntity.id").entity(UUID.class).isEqualTo(facilityRoleTypeEntity.getId())
				.path("addFacilityRole.facilityRoleTypeEntity.description").entity(String.class).isEqualTo(facilityRoleTypeEntity.getDescription())
				.path("addFacilityRole.partyEntity.id").entity(UUID.class).isEqualTo(partyEntity.getId())
				.path("addFacilityRole.partyEntity.partyTypeEntity.id").entity(UUID.class).isEqualTo(partyTypeEntity.getId())
				.path("addFacilityRole.partyEntity.partyTypeEntity.description").entity(String.class).isEqualTo(partyTypeEntity.getDescription());
	}
}
