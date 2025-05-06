package org.erpmicroservices.peopleandorganizations.endpoint.builders;

import org.erpmicroservices.peopleandorganizations.backend.entities.CaseEntity;

import java.util.UUID;

import static org.erpmicroservices.peopleandorganizations.endpoint.builders.DateTimeTestDataBuilder.zonedDateTimeNow;

public class CaseTestDataBuilder {

	public static CaseEntity.CaseEntityBuilder completeCase() {
		return CaseEntity.builder()
				       .description("Complete case " + UUID.randomUUID())
				       .startedAt(zonedDateTimeNow());
	}
}
