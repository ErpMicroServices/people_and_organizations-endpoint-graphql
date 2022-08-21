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

	protected FacilityRole aFacilityRoleExists() {
		if (facility == null) {
			aFacilityExists();
		}
		if (facilityRoleType == null) {
			aFacilityRoleTypeExists();
		}
		if (party == null) {
			aPartyExists();
		}
		facilityRole = facilityRoleRepository.save(FacilityRole.builder()
				                                           .facilityId(facility.getId())
				                                           .facilityRoleTypeId(facilityRoleType.getId())
				                                           .partyId(party.getId())
				                                           .fromDate(LocalDate.now())
				                                           .build());
		return facilityRole;
	}

	protected FacilityRoleType aFacilityRoleTypeExists() {
		facilityRoleType = facilityRoleTypeRepository.save(FacilityRoleType.builder()
				                                                   .description("FacilityRoleType Test Data " + UUID.randomUUID())
				                                                   .build());
		return facilityRoleType;
	}

	protected FacilityContactMechanism aFacilityContactMechanismExists() {
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
		return facilityContactMechanism;
	}

	protected ContactMechanism aContactMechanismExists() {
		if (contactMechanismType == null) {
			aContactMechanismTypeExists();
		}
		contactMechanism = contactMechanismRepository.save(ContactMechanism.builder()
				                                                   .endPoint("ContactMechanism Test Data endPoint " + UUID.randomUUID())
				                                                   .directions("ContactMechanism Test Data directions " + UUID.randomUUID())
				                                                   .contactMechanismTypeId(contactMechanismType.getId())
				                                                   .build());
		return contactMechanism;
	}

	protected ContactMechanismGeographicBoundary aGeographicBoundaryBelongsToAContactMechanism() {
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
		return contactMechanismGeographicBoundary;
	}

	protected GeographicBoundary aGeographicBoundaryExists() {
		geographicBoundary = geographicBoundaryRepository.save(GeographicBoundary.builder()
				                                                       .abbreviation("GeographicBoundary.abbreviation test data " + UUID.randomUUID())
				                                                       .geoCode("GeographicBoundary.geocode test data " + UUID.randomUUID())
				                                                       .geographicBoundaryTypeId(geographicBoundaryType.getId())
				                                                       .name("geographicBoundary.name test data " + UUID.randomUUID())
				                                                       .build());
		return geographicBoundary;
	}

	protected GeographicBoundaryType aGeographicBoundaryTypeExists() {
		geographicBoundaryType = geographicBoundaryTypeRepository.save(GeographicBoundaryType.builder()
				                                                               .description("geographicBoundaryType test data " + UUID.randomUUID())
				                                                               .build());
		return geographicBoundaryType;
	}

	protected Facility aFacilityExists() {
		if (facilityType == null) {
			aFacilityTypeExists();
		}
		facility = facilityRepository.save(Facility.builder()
				                                   .facilityTypeId(facilityType.getId())
				                                   .squareFootage(100L)
				                                   .description("Facility Test Data " + UUID.randomUUID())
				                                   .build());
		return facility;
	}

	protected FacilityType aFacilityTypeExists() {
		facilityType = facilityTypeRepository.save(FacilityType.builder()
				                                           .description("FacilityType Test Data " + UUID.randomUUID())
				                                           .build());
		return facilityType;
	}
}
