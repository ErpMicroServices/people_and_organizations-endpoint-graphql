package org.erpmicroservices.peopleandorganizations.endpoint.graphql.facilty;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.facility.Facility;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.facility.FacilityType;

import java.util.Map;

public class CreateFacilityTest extends FacilityGwtTemplate {

	private Facility facility;

	@Override
	public void given() {
		super.given();
		aFacilityTypeExists();
		facility = Facility.builder()
				           .description("facility for testing ")
				           .squareFootage(100L)
				           .facilityTypeId(facilityType.getId())
				           .build();
	}

	@Override
	public void when() {
		response = graphQlTester.documentName("CreateFacility")
				           .operationName("CreateFacility")
				           .variable("newFacility", Map.of(
						           "facilityTypeId", facility.getFacilityTypeId(),
						           "description", facility.getDescription(),
						           "squareFootage", facility.getSquareFootage()
				           ))
				           .execute();
	}

	@Override
	public void then() {
		response
				.path("createFacility.id").hasValue()
				.path("createFacility.description").entity(String.class).isEqualTo(facility.getDescription())
				.path("createFacility.facilityType").entity(FacilityType.class).isEqualTo(facilityType);
	}
}
