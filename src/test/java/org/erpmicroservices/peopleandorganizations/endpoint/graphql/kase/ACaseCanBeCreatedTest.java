package org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;
import java.util.UUID;

import static org.erpmicroservices.peopleandorganizations.endpoint.builders.CaseStatusTypeTestDataBuilder.completeCaseStatusType;
import static org.erpmicroservices.peopleandorganizations.endpoint.builders.CaseTestDataBuilder.completeCase;
import static org.erpmicroservices.peopleandorganizations.endpoint.builders.CaseTypeTestDataBuilder.completeCaseType;

@SpringBootTest
@AutoConfigureGraphQlTester
public class ACaseCanBeCreatedTest extends AbstractGWT {

	private final String caseCreateGraphQlPath = "createCase.";

	private CaseType caseType;
	private CaseStatusType caseStatusType;
	private Case aCase;

	@BeforeEach
	@Override
	public void givenTheFollowing() {
		super.givenTheFollowing();
		caseType = caseTypeRepository.save(completeCaseType().build());
		caseStatusType = caseStatusTypeRepository.save(completeCaseStatusType().build());
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
						           "startedAt", aCase.getStartedAt(),
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
				.path(caseCreateGraphQlPath + "startedAt").entity(String.class).isEqualTo(aCase.getStartedAt().toString())
				.path(caseCreateGraphQlPath + "caseType.id").entity(UUID.class).isEqualTo(caseType.getId())
				.path(caseCreateGraphQlPath + "caseType.description").entity(String.class).isEqualTo(caseType.getDescription())
				.path(caseCreateGraphQlPath + "caseStatusType.id").entity(UUID.class).isEqualTo(caseStatusType.getId())
				.path(caseCreateGraphQlPath + "caseStatusType.description").entity(String.class).isEqualTo(caseStatusType.getDescription());
	}
}
