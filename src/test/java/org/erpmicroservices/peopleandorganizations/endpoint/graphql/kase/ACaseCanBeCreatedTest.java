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
	public void given() {
		super.given();
		aCaseTypeExists();
		aCaseStatusTypeExists();
		aCaseEntity = completeCase()
				        .caseTypeId(caseTypeEntity.getId())
				        .caseStatusTypeId(caseStatusTypeEntity.getId())
				        .build();
	}

	@Test
	@Override
	public void when() {
		response = this.graphQlTester.documentName("caseCreate")
				           .operationName("CreateCase")
				           .variable("newCase", Map.of(
						           "description", aCaseEntity.getDescription(),
						           "startedAt", aCaseEntity.getStartedAt().format(DateTimeFormatter.ISO_DATE_TIME),
						           "caseTypeId", caseTypeEntity.getId(),
						           "caseStatusTypeId", caseStatusTypeEntity.getId()
				           ))
				           .execute();
	}

	@AfterEach
	@Override
	public void then() {
		response.path(caseCreateGraphQlPath + "id").hasValue()
				.path(caseCreateGraphQlPath + "description").entity(String.class).isEqualTo(aCaseEntity.getDescription())
				.path(caseCreateGraphQlPath + "startedAt").entity(ZonedDateTime.class).matches((ZonedDateTime s) -> s.isEqual(aCaseEntity.getStartedAt()))
				.path(caseCreateGraphQlPath + "caseTypeEntity.id").entity(UUID.class).isEqualTo(caseTypeEntity.getId())
				.path(caseCreateGraphQlPath + "caseTypeEntity.description").entity(String.class).isEqualTo(caseTypeEntity.getDescription())
				.path(caseCreateGraphQlPath + "caseStatusTypeEntity.id").entity(UUID.class).isEqualTo(caseStatusTypeEntity.getId())
				.path(caseCreateGraphQlPath + "caseStatusTypeEntity.description").entity(String.class).isEqualTo(caseStatusTypeEntity.getDescription());
	}
}
