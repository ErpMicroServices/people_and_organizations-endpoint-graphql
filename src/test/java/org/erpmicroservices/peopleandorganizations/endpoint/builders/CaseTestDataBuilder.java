package org.erpmicroservices.peopleandorganizations.endpoint.builders;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.Case;

import java.util.UUID;

import static org.erpmicroservices.peopleandorganizations.endpoint.builders.DateTimeTestDataBuilder.zonedDateTimeNow;

public class CaseTestDataBuilder {

	public static Case.CaseBuilder completeCase() {
		return Case.builder()
				       .description("Complete case " + UUID.randomUUID())
				       .startedAt(zonedDateTimeNow());
	}
}
