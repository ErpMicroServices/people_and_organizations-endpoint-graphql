package org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@AutoConfigureGraphQlTester
public class CaseTypeQueryTest extends KaseGwtTemplate {

	private final List<CaseType> children = new ArrayList<>();
	private CaseType parent;

	@BeforeEach
	@Override
	public void given() {
		super.given();
		aCaseTypeWithChildrenExists(5);
	}

	@Test
	@Override
	public void when() {
		response = this.graphQlTester.documentName("CaseTypeQuery")
				           .operationName("CaseTypeQuery")
				           .variable("childrenPageInfo", pageInfoSortingOn("description"))
				           .execute();
	}

	@AfterEach
	@Override
	public void then() {
		response
				.path("caseTypes.edges").entityList(CaseType.class).hasSize(1)
				.path("caseTypes.edges[0].node.id").entity(UUID.class).isEqualTo(parent.getId())
				.path("caseTypes.edges[0].node.description").entity(String.class).isEqualTo(parent.getDescription())
				.path("caseTypes.edges[0].node.parent").valueIsNull()
				.path("caseTypes.edges[0].node.children.edges").entityList(CaseTypeEdge.class).hasSize(5)
				.path("caseTypes.edges[0].node.children.edges").entityList(CaseTypeEdge.class).contains(children.stream()
						                                                                                        .map(caseType1 -> CaseType.builder()
								                                                                                                          .description(caseType.getDescription())
								                                                                                                          .id(caseType.getId())
								                                                                                                          .build())
						                                                                                        .sorted((caseType1, caseType2) ->
								                                                                                                caseType1.getDescription().compareTo(caseType2.getDescription()))
						                                                                                        .map(caseType1 -> CaseTypeEdge.builder()
								                                                                                                          .node(caseType1)
								                                                                                                          .build())
						                                                                                        .toArray(CaseTypeEdge[]::new));
	}

	private void aCaseTypeWithChildrenExists(final int numberOfChildren) {
		parent = aCaseTypeExists();
		for (int i = 0; i < numberOfChildren; i++) {
			CaseType child = aCaseTypeExists();
			child.setParentId(parent.getId());
			child = caseTypeRepository.save(child);
			children.add(child);
		}
	}
}
