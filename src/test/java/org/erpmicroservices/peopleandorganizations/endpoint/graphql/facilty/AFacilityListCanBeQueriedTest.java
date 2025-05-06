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
public class AFacilityListCanBeQueriedTest extends FacilityGwtTemplate {

	public static final String facilitiesNodeRoot = "facilities.edges[0].node.";
	public static final String facilityTypeRoot = facilitiesNodeRoot + "facilityTypeEntity.";
	public static final String facilityContactMechanismsRoot = facilitiesNodeRoot + "contactMechanisms.edges[0].node.";
	public static final String contactMechanismRoot = facilityContactMechanismsRoot + "contactMechanismEntity.";

	public static final String geographicBoundariesRoot = contactMechanismRoot + "geographicBoundaries.edges[0].node.";

	@BeforeEach
	@Override
	public void given() {
		super.given();
		aFacilityExists();
		aFacilityContactMechanismExists();
		aGeographicBoundaryThatBelongsToAContactMechanismExists();
		aFacilityRoleExists();
	}


	@Test
	@Override
	public void when() {
		response = graphQlTester.documentName("FacilityQuery")
				           .operationName("Facilities")
				           .variable("facilityPageInfo", pageInfoSortingOn("description"))
				           .variable("facilityContactMechanismsPageInfo", pageInfoSortingOn("fromDate"))
				           .variable("geographicBoundaryPageInfo", pageInfoSortingOn("name"))
				           .variable("rolesPageInfo", pageInfoSortingOn("fromDate"))
				           .execute();
	}

	@AfterEach
	@Override
	public void then() {
		response.path(facilitiesNodeRoot + "id").entity(UUID.class).isEqualTo(facilityEntity.getId())
				.path(facilitiesNodeRoot + "description").entity(String.class).isEqualTo(facilityEntity.getDescription())
				.path(facilityTypeRoot + "id").entity(UUID.class).isEqualTo(facilityTypeEntity.getId())
				.path(facilityTypeRoot + "description").entity(String.class).isEqualTo(facilityTypeEntity.getDescription())
				.path(facilityContactMechanismsRoot + "id").entity(UUID.class).isEqualTo(facilityContactMechanismEntity.getId())
				.path(facilityContactMechanismsRoot + "fromDate").entity(LocalDate.class).isEqualTo(facilityContactMechanismEntity.getFromDate())
				.path(facilityContactMechanismsRoot + "fromDate").entity(LocalDate.class).isEqualTo(facilityContactMechanismEntity.getFromDate())
				.path(contactMechanismRoot + "id").entity(UUID.class).isEqualTo(contactMechanismEntity.getId())
				.path(contactMechanismRoot + "endPoint").entity(String.class).isEqualTo(contactMechanismEntity.getEndPoint())
				.path(contactMechanismRoot + "contactMechanismTypeEntity.id").entity(UUID.class).isEqualTo(contactMechanismTypeEntity.getId())
				.path(contactMechanismRoot + "contactMechanismTypeEntity.description").entity(String.class).isEqualTo(contactMechanismTypeEntity.getDescription())
				.path(geographicBoundariesRoot + "id").entity(UUID.class).isEqualTo(geographicBoundaryEntity.getId())
				.path(geographicBoundariesRoot + "abbreviation").entity(String.class).isEqualTo(geographicBoundaryEntity.getAbbreviation())
				.path(geographicBoundariesRoot + "geoCode").entity(String.class).isEqualTo(geographicBoundaryEntity.getGeoCode())
				.path(geographicBoundariesRoot + "name").entity(String.class).isEqualTo(geographicBoundaryEntity.getName())
				.path(geographicBoundariesRoot + "geographicBoundaryTypeEntity.id").entity(UUID.class).isEqualTo(geographicBoundaryTypeEntity.getId())
				.path(geographicBoundariesRoot + "geographicBoundaryTypeEntity.description").entity(String.class).isEqualTo(geographicBoundaryTypeEntity.getDescription());

	}
}
