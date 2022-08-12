package org.erpmicroservices.peopleandorganizations.endpoint.graphql.facilty;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
@AutoConfigureGraphQlTester
public class AFacilityListCanBeQueried extends FacilityGwtTemplate {

	public static final String facilitiesNodeRoot = "facilities.edges[0].node.";
	public static final String facilityTypeRoot = facilitiesNodeRoot + "facilityType.";
	public static final String facilityContactMechanismsRoot = facilitiesNodeRoot + "contactMechanisms.edges[0].node.";
	public static final String contactMechanismRoot = facilityContactMechanismsRoot + "contactMechanism.";

	public static final String geographicBoundariesRoot = contactMechanismRoot + "geographicBoundaries.edges[0].node.";

	@BeforeEach
	@Override
	public void givenTheFollowing() {
		super.givenTheFollowing();
		aFacilityExists();
		aFacilityContactMechanismExists();
		aGeographicBoundaryBelongsToAContactMechanism();
		aFacilityRoleExists();
	}


	@Test
	@Override
	public void whenThisHappens() {
		response = graphQlTester.documentName("FacilityQuery")
				           .operationName("Facilities")
				           .variable("facilityPageInfo", pageInfoSortingOn("description"))
				           .variable("facilityContactMechanismsPageInfo", pageInfoSortingOn("fromDate"))
				           .variable("geographicBoundaryPageInfo", pageInfoSortingOn("id"))
				           .variable("rolesPageInfo", pageInfoSortingOn("fromDate"))
				           .execute();
	}

	@AfterEach
	@Override
	public void thenThisIsExpected() {
		response.path(facilitiesNodeRoot + "id").entity(UUID.class).isEqualTo(facility.getId())
				.path(facilitiesNodeRoot + "description").entity(String.class).isEqualTo(facility.getDescription())
				.path(facilityTypeRoot + "id").entity(UUID.class).isEqualTo(facilityType.getId())
				.path(facilityTypeRoot + "description").entity(String.class).isEqualTo(facilityType.getDescription())
				.path(facilityContactMechanismsRoot + "id").entity(UUID.class).isEqualTo(facilityContactMechanism.getId())
				.path(facilityContactMechanismsRoot + "fromDate").entity(LocalDate.class).isEqualTo(facilityContactMechanism.getFromDate())
				.path(facilityContactMechanismsRoot + "fromDate").entity(LocalDate.class).isEqualTo(facilityContactMechanism.getFromDate())
				.path(contactMechanismRoot + "id").entity(UUID.class).isEqualTo(contactMechanism.getId())
				.path(contactMechanismRoot + "endPoint").entity(String.class).isEqualTo(contactMechanism.getEndPoint())
				.path(contactMechanismRoot + "contactMechanismType.id").entity(UUID.class).isEqualTo(contactMechanismType.getId())
				.path(contactMechanismRoot + "contactMechanismType.description").entity(String.class).isEqualTo(contactMechanismType.getDescription())
				.path(geographicBoundariesRoot + "id").entity(UUID.class).isEqualTo(geographicBoundary.getId())
				.path(geographicBoundariesRoot + "abbreviation").entity(String.class).isEqualTo(geographicBoundary.getAbbreviation())
				.path(geographicBoundariesRoot + "geoCode").entity(String.class).isEqualTo(geographicBoundary.getGeoCode())
				.path(geographicBoundariesRoot + "name").entity(String.class).isEqualTo(geographicBoundary.getName())
				.path(geographicBoundariesRoot + "geographicBoundaryType.id").entity(UUID.class).isEqualTo(geographicBoundaryType.getId())
				.path(geographicBoundariesRoot + "geographicBoundaryType.description").entity(String.class).isEqualTo(geographicBoundaryType.getDescription());

	}
}
