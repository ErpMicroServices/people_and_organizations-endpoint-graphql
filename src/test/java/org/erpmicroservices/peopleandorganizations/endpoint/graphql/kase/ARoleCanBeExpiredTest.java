package org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
@AutoConfigureGraphQlTester
public class ARoleCanBeExpiredTest extends KaseGwtTemplate {


	@BeforeEach
	@Override
	public void givenTheFollowing() {
		super.givenTheFollowing();
		aCaseExists();
		aCaseRoleExists();
	}

	@Test
	@Override
	public void whenThisHappens() {
		response = this.graphQlTester.documentName("ExpireCaseRole")
				           .operationName("ExpireCaseRole")
				           .variable("caseId", aCase.getId())
				           .variable("caseRoleId", caseRole.getId())
				           .execute();
	}

	@AfterEach
	@Override
	public void thenThisIsExpected() {
		response.path("expireCaseRole.id").entity(UUID.class).isEqualTo(caseRole.getId())
				.path("expireCaseRole.fromDate").entity(LocalDate.class).isEqualTo(caseRole.getFromDate())
				.path("expireCaseRole.thruDate").hasValue()
				.path("expireCaseRole.party.id").entity(UUID.class).isEqualTo(party1.getId())
				.path("expireCaseRole.caseRoleType.id").entity(UUID.class).isEqualTo(caseRoleType.getId());
	}
}
