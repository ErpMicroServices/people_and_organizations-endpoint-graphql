package org.erpmicroservices.peopleandorganizations.endpoint.builders;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.Case;

import java.time.ZonedDateTime;
import java.util.UUID;

public class CaseTestDataBuilder {

	public static Case.CaseBuilder completeCase() {
		return Case.builder()
				       .description("Complete case " + UUID.randomUUID())
				       .startedAt(ZonedDateTime.now());
	}
}
