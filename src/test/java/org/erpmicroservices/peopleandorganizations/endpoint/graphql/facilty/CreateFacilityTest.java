package org.erpmicroservices.peopleandorganizations.endpoint.graphql.facilty;

import org.erpmicroservices.peopleandorganizations.backend.entities.FacilityEntity;
import org.erpmicroservices.peopleandorganizations.backend.entities.FacilityTypeEntity;

import java.util.Map;

public class CreateFacilityTest extends FacilityGwtTemplate {

	private FacilityEntity facilityEntity;

	@Override
	public void given() {
		super.given();
		aFacilityTypeExists();
		facilityEntity = FacilityEntity.builder()
				           .description("facilityEntity for testing ")
				           .squareFootage(100L)
				           .facilityTypeId(facilityTypeEntity.getId())
				           .build();
	}

	@Override
	public void when() {
		response = graphQlTester.documentName("CreateFacility")
				           .operationName("CreateFacility")
				           .variable("newFacility", Map.of(
						           "facilityTypeId", facilityEntity.getFacilityTypeId(),
						           "description", facilityEntity.getDescription(),
						           "squareFootage", facilityEntity.getSquareFootage()
				           ))
				           .execute();
	}

	@Override
	public void then() {
		response
				.path("createFacility.id").hasValue()
				.path("createFacility.description").entity(String.class).isEqualTo(facilityEntity.getDescription())
				.path("createFacility.facilityTypeEntity").entity(FacilityTypeEntity.class).isEqualTo(facilityTypeEntity);
	}
}
