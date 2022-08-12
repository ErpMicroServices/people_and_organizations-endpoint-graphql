package org.erpmicroservices.peopleandorganizations.endpoint.graphql.facilty;

import org.erpmicroservices.peopleandorganizations.endpoint.AbstractGWT;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.contactmechanism.ContactMechanism;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.contactmechanism.ContactMechanismGeographicBoundary;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.facility.*;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.geographicboundary.GeographicBoundary;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.geographicboundary.GeographicBoundaryType;

import java.time.LocalDate;
import java.util.UUID;

public abstract class FacilityGwtTemplate extends AbstractGWT {
	protected Facility facility;
	protected FacilityType facilityType;
	protected ContactMechanism contactMechanism;
	protected FacilityContactMechanism facilityContactMechanism;
	protected FacilityRoleType facilityRoleType;
	protected FacilityRole facilityRole;
	protected GeographicBoundary geographicBoundary;
	protected GeographicBoundaryType geographicBoundaryType;
	protected ContactMechanismGeographicBoundary contactMechanismGeographicBoundary;

	protected void aFacilityRoleExists() {
		if (facility == null) {
			aFacilityExists();
		}
		if (facilityRoleType == null) {
			aFacilityRoleTypeExists();
		}
		if (party1 == null) {
			party1 = aPartyExists();
		}
		facilityRole = facilityRoleRepository.save(FacilityRole.builder()
				                                           .facilityId(facility.getId())
				                                           .facilityRoleTypeId(facilityRoleType.getId())
				                                           .partyId(party1.getId())
				                                           .fromDate(LocalDate.now())
				                                           .build());
	}

	private void aFacilityRoleTypeExists() {
		facilityRoleType = facilityRoleTypeRepository.save(FacilityRoleType.builder()
				                                                   .description("FacilityRoleType Test Data " + UUID.randomUUID())
				                                                   .build());
	}

	protected void aFacilityContactMechanismExists() {
		if (facility == null) {
			aFacilityExists();
		}
		if (contactMechanism == null) {
			aContactMechanismExists();
		}
		facilityContactMechanism = facilityContactMechanismRepository.save(FacilityContactMechanism.builder()
				                                                                   .contactMechanismId(contactMechanism.getId())
				                                                                   .facilityId(facility.getId())
				                                                                   .fromDate(LocalDate.now())
				                                                                   .build());
	}

	protected void aContactMechanismExists() {
		if (contactMechanismType == null) {
			aContactMechanismTypeExists();
		}
		contactMechanism = contactMechanismRepository.save(ContactMechanism.builder()
				                                                   .endPoint("ContactMechanism Test Data endPoint " + UUID.randomUUID())
				                                                   .directions("ContactMechanism Test Data directions " + UUID.randomUUID())
				                                                   .contactMechanismTypeId(contactMechanismType.getId())
				                                                   .build());
	}

	protected void aGeographicBoundaryBelongsToAContactMechanism() {
		if (geographicBoundaryType == null) {
			aGeographicBoundaryTypeExists();
		}
		if (geographicBoundary == null) {
			aGeographicBoundaryExists();
		}
		contactMechanismGeographicBoundary = contactMechanismGeographicBoundaryRepository.save(ContactMechanismGeographicBoundary.builder()
				                                                                                       .geographicBoundaryId(geographicBoundary.getId())
				                                                                                       .contactMechanismId(contactMechanism.getId())
				                                                                                       .build());
	}

	protected void aGeographicBoundaryExists() {
		geographicBoundary = geographicBoundaryRepository.save(GeographicBoundary.builder()
				                                                       .abbreviation("GeographicBoundary.abbreviation test data " + UUID.randomUUID())
				                                                       .geoCode("GeographicBoundary.geocode test data " + UUID.randomUUID())
				                                                       .geographicBoundaryTypeId(geographicBoundaryType.getId())
				                                                       .name("geographicBoundary.name test data " + UUID.randomUUID())
				                                                       .build());
	}

	protected void aGeographicBoundaryTypeExists() {
		geographicBoundaryType = geographicBoundaryTypeRepository.save(GeographicBoundaryType.builder()
				                                                               .description("geographicBoundaryType test data " + UUID.randomUUID())
				                                                               .build());
	}

	protected void aFacilityExists() {
		if (facilityType == null) {
			aFacilityTypeExists();
		}
		facility = facilityRepository.save(Facility.builder()
				                                   .facilityTypeId(facilityType.getId())
				                                   .squareFootage(100L)
				                                   .description("Facility Test Data " + UUID.randomUUID())
				                                   .build());
	}

	private void aFacilityTypeExists() {
		facilityType = facilityTypeRepository.save(FacilityType.builder()
				                                           .description("FacilityType Test Data " + UUID.randomUUID())
				                                           .build());
	}
}
