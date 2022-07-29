package org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;


@SpringBootTest
@AutoConfigureGraphQlTester
public class CaseControllerTest {

	@Autowired
	private GraphQlTester graphQlTester;

	@Test
	void querydslRepositorySingle() {
		this.graphQlTester.documentName("artifactRepository")
				.variable("id", "spring-releases")
				.execute()
				.path("artifactRepository.name")
				.entity(String.class).isEqualTo("Spring Releases");
	}
}
