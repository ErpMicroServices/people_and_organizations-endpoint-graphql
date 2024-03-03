package org.erpmicroservices.peopleandorganizations.endpoint.graphql.facilty;

import org.erpmicroservices.peopleandorganizations.backend.entities.*;
import org.erpmicroservices.peopleandorganizations.endpoint.AbstractGWT;

import java.time.LocalDate;
import java.util.UUID;

public abstract class FacilityGwtTemplate extends AbstractGWT {
	protected FacilityEntity facilityEntity;
	protected FacilityTypeEntity facilityTypeEntity;
	protected FacilityContactMechanismEntity facilityContactMechanismEntity;
	protected FacilityRoleTypeEntity facilityRoleTypeEntity;
	protected FacilityRoleEntity facilityRoleEntity;

	protected FacilityRoleEntity aFacilityRoleExists() {
		if (facilityEntity == null) {
			aFacilityExists();
		}
		if (facilityRoleTypeEntity == null) {
			aFacilityRoleTypeExists();
		}
		if (partyEntity == null) {
			aPartyExists();
		}
		facilityRoleEntity = facilityRoleRepository.save(FacilityRoleEntity.builder()
				                                           .facilityId(facilityEntity.getId())
				                                           .facilityRoleTypeId(facilityRoleTypeEntity.getId())
				                                           .partyId(partyEntity.getId())
				                                           .fromDate(LocalDate.now())
				                                           .build());
		return facilityRoleEntity;
	}

	protected FacilityRoleTypeEntity aFacilityRoleTypeExists() {
		facilityRoleTypeEntity = facilityRoleTypeRepository.save(FacilityRoleTypeEntity.builder()
				                                                   .description("FacilityRoleTypeEntity Test Data " + UUID.randomUUID())
				                                                   .build());
		return facilityRoleTypeEntity;
	}

	protected FacilityContactMechanismEntity aFacilityContactMechanismExists() {
		if (facilityEntity == null) {
			aFacilityExists();
		}
		if (contactMechanismEntity == null) {
			aContactMechanismExists();
		}
		facilityContactMechanismEntity = facilityContactMechanismRepository.save(FacilityContactMechanismEntity.builder()
				                                                                   .contactMechanismId(contactMechanismEntity.getId())
				                                                                   .facilityId(facilityEntity.getId())
				                                                                   .fromDate(LocalDate.now())
				                                                                   .build());
		return facilityContactMechanismEntity;
	}

	protected FacilityEntity aFacilityExists() {
		if (facilityTypeEntity == null) {
			aFacilityTypeExists();
		}
		facilityEntity = facilityRepository.save(FacilityEntity.builder()
				                                   .facilityTypeId(facilityTypeEntity.getId())
				                                   .squareFootage(100L)
				                                   .description("FacilityEntity Test Data " + UUID.randomUUID())
				                                   .build());
		return facilityEntity;
	}

	protected FacilityTypeEntity aFacilityTypeExists() {
		facilityTypeEntity = facilityTypeRepository.save(FacilityTypeEntity.builder()
				                                           .description("FacilityTypeEntity Test Data " + UUID.randomUUID())
				                                           .build());
		return facilityTypeEntity;
	}
}
