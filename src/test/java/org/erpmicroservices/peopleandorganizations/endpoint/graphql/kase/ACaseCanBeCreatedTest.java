package org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.UUID;

import static org.erpmicroservices.peopleandorganizations.endpoint.builders.CaseTestDataBuilder.completeCase;

@SpringBootTest
@AutoConfigureGraphQlTester
public class ACaseCanBeCreatedTest extends KaseGwtTemplate {

	private final String caseCreateGraphQlPath = "createCase.";

	@BeforeEach
	@Override
	public void givenTheFollowing() {
		super.givenTheFollowing();
		aCaseTypeExists();
		aCaseStatusTypeExists();
		aCase = completeCase()
				        .caseTypeId(caseType.getId())
				        .caseTypeId(caseStatusType.getId())
				        .build();
	}

	@Test
	@Override
	public void whenThisHappens() {
		response = this.graphQlTester.documentName("caseCreate")
				           .operationName("CreateCase")
				           .variable("newCase", Map.of(
						           "description", aCase.getDescription(),
						           "startedAt", aCase.getStartedAt().format(DateTimeFormatter.ISO_DATE_TIME),
						           "caseTypeId", caseType.getId(),
						           "caseStatusTypeId", caseStatusType.getId()
				           ))
				           .execute();
	}

	@AfterEach
	@Override
	public void thenThisIsExpected() {
		response.path(caseCreateGraphQlPath + "id").hasValue()
				.path(caseCreateGraphQlPath + "description").entity(String.class).isEqualTo(aCase.getDescription())
				.path(caseCreateGraphQlPath + "startedAt").entity(ZonedDateTime.class).matches((ZonedDateTime s) -> s.isEqual(aCase.getStartedAt()))
				.path(caseCreateGraphQlPath + "caseType.id").entity(UUID.class).isEqualTo(caseType.getId())
				.path(caseCreateGraphQlPath + "caseType.description").entity(String.class).isEqualTo(caseType.getDescription())
				.path(caseCreateGraphQlPath + "caseStatusType.id").entity(UUID.class).isEqualTo(caseStatusType.getId())
				.path(caseCreateGraphQlPath + "caseStatusType.description").entity(String.class).isEqualTo(caseStatusType.getDescription());
	}
}
