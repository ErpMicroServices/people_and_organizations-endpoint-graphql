package org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase;

import org.erpmicroservices.peopleandorganizations.backend.entities.CaseStatusTypeEntity;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.models.CaseStatusTypeEdge;
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
public class CaseStatusTypeQueryTest extends KaseGwtTemplate {

	private final List<CaseStatusTypeEntity> children = new ArrayList<>();
	private CaseStatusTypeEntity parent;

	@BeforeEach
	@Override
	public void given() {
		super.given();
		aCaseStatusTypeWithChildrenExists(5);
	}

	@Test
	@Override
	public void when() {
		response = this.graphQlTester.documentName("CaseStatusTypeQuery")
				           .operationName("CaseStatusTypeQuery")
				           .variable("childrenPageInfo", pageInfoSortingOn("description"))
				           .execute();
	}

	@AfterEach
	@Override
	public void then() {
		response
				.path("caseStatusTypes.edges").entityList(CaseStatusTypeEntity.class).hasSize(1)
				.path("caseStatusTypes.edges[0].node.id").entity(UUID.class).isEqualTo(parent.getId())
				.path("caseStatusTypes.edges[0].node.description").entity(String.class).isEqualTo(parent.getDescription())
				.path("caseStatusTypes.edges[0].node.parent").valueIsNull()
				.path("caseStatusTypes.edges[0].node.children.edges").entityList(CaseStatusTypeEdge.class).hasSize(5)
				.path("caseStatusTypes.edges[0].node.children.edges").entityList(CaseStatusTypeEdge.class).contains(children.stream()
						                                                                                                    .map(caseStatusType1 -> CaseStatusTypeEntity.builder()
								                                                                                                                            .description(caseStatusTypeEntity.getDescription())
								                                                                                                                            .id(caseStatusTypeEntity.getId())
								                                                                                                                            .build())
						                                                                                                    .sorted((caseStatusType1, caseStatusType2) ->
								                                                                                                            caseStatusType1.getDescription().compareTo(caseStatusType2.getDescription()))
						                                                                                                    .map(caseStatusType1 -> CaseStatusTypeEdge.builder()
								                                                                                                                            .node(caseStatusType1)
								                                                                                                                            .build())
						                                                                                                    .toArray(CaseStatusTypeEdge[]::new));
	}

	private void aCaseStatusTypeWithChildrenExists(final int numberOfChildren) {
		parent = aCaseStatusTypeExists();
		for (int i = 0; i < numberOfChildren; i++) {
			CaseStatusTypeEntity child = aCaseStatusTypeExists();
			child.setParentId(parent.getId());
			child = caseStatusTypeRepository.save(child);
			children.add(child);
		}
	}
}
