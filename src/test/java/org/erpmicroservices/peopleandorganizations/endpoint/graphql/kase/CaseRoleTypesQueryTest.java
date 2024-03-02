package org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.models.CaseRoleTypeEdge;
import org.erpmicroservices.peopleandorganizations.backend.entities.CaseRoleType;
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
public class CaseRoleTypesQueryTest extends KaseGwtTemplate {

	private final List<CaseRoleType> children = new ArrayList<>();
	private CaseRoleType parent;

	@BeforeEach
	@Override
	public void given() {
		super.given();
		aCaseRoleTypeWithChildrenExists(5);
	}

	@Test
	@Override
	public void when() {
		response = this.graphQlTester.documentName("CaseRoletypesQuery")
				           .operationName("CaseRoleTypesQuery")
				           .variable("childrenPageInfo", pageInfoSortingOn("description"))
				           .execute();
	}

	@AfterEach
	@Override
	public void then() {
		response
				.path("caseRoleTypes.edges").entityList(CaseRoleType.class).hasSize(1)
				.path("caseRoleTypes.edges[0].node.id").entity(UUID.class).isEqualTo(parent.getId())
				.path("caseRoleTypes.edges[0].node.description").entity(String.class).isEqualTo(parent.getDescription())
				.path("caseRoleTypes.edges[0].node.parent").valueIsNull()
				.path("caseRoleTypes.edges[0].node.children.edges").entityList(CaseRoleTypeEdge.class).hasSize(5)
				.path("caseRoleTypes.edges[0].node.children.edges").entityList(CaseRoleTypeEdge.class).contains(children.stream()
						                                                                                                .map(caseRoleType1 -> CaseRoleType.builder()
								                                                                                                                      .description(caseRoleType.getDescription())
								                                                                                                                      .id(caseRoleType.getId())
								                                                                                                                      .build())
						                                                                                                .sorted((caseRoleType1, caseRoleType2) ->
								                                                                                                        caseRoleType1.getDescription().compareTo(caseRoleType2.getDescription()))
						                                                                                                .map(caseRoleType1 -> CaseRoleTypeEdge.builder()
								                                                                                                                      .node(caseRoleType1)
								                                                                                                                      .build())
						                                                                                                .toArray(CaseRoleTypeEdge[]::new));
	}

	private void aCaseRoleTypeWithChildrenExists(final int numberOfChildren) {
		parent = aCaseRoleTypeExists();
		for (int i = 0; i < numberOfChildren; i++) {
			CaseRoleType child = aCaseRoleTypeExists();
			child.setParentId(parent.getId());
			child = caseRoleTypeRepository.save(child);
			children.add(child);
		}
	}
}
