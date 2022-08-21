package org.erpmicroservices.peopleandorganizations.endpoint.graphql.facilty;

import org.erpmicroservices.peopleandorganizations.endpoint.AbstractGWT;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.facility.*;

import java.time.LocalDate;
import java.util.UUID;

public abstract class FacilityGwtTemplate extends AbstractGWT {
	protected Facility facility;
	protected FacilityType facilityType;
	protected FacilityContactMechanism facilityContactMechanism;
	protected FacilityRoleType facilityRoleType;
	protected FacilityRole facilityRole;

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
