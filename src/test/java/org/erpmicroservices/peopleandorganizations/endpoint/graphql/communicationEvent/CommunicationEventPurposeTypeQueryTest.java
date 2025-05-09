package org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationEvent;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.models.CommunicationEventPurposeTypeEdge;
import org.erpmicroservices.peopleandorganizations.backend.entities.CommunicationEventPurposeTypeEntity;
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
public class CommunicationEventPurposeTypeQueryTest extends CommunicationEventGwtTemplateTemplate {

	private final List<CommunicationEventPurposeTypeEntity> children = new ArrayList<>();
	private CommunicationEventPurposeTypeEntity parent;

	@BeforeEach
	@Override
	public void given() {
		super.given();
		aCommunicationEventPurposeTypeWithChildrenExists(5);
	}

	private void aCommunicationEventPurposeTypeWithChildrenExists(final int numberOfChildren) {
		parent = aCommunicationEventPurposeTypeExists();
		for (int i = 0; i < numberOfChildren; i++) {
			CommunicationEventPurposeTypeEntity child = aCommunicationEventPurposeTypeExists();
			child.setParentId(parent.getId());
			child = communicationEventPurposeTypeRepository.save(child);
			children.add(child);
		}
	}

	@Test
	@Override
	public void when() {
		response = this.graphQlTester.documentName("CommunicationEventPurposeTypeQuery")
				           .operationName("CommunicationEventPurposeTypeQuery")
				           .variable("childrenPageInfo", pageInfoSortingOn("description"))
				           .execute();
	}

	@AfterEach
	@Override
	public void then() {
		response
				.path("communicationEventPurposeTypes.edges").entityList(CommunicationEventPurposeTypeEntity.class).hasSize(1)
				.path("communicationEventPurposeTypes.edges[0].node.id").entity(UUID.class).isEqualTo(parent.getId())
				.path("communicationEventPurposeTypes.edges[0].node.description").entity(String.class).isEqualTo(parent.getDescription())
				.path("communicationEventPurposeTypes.edges[0].node.parent").valueIsNull()
				.path("communicationEventPurposeTypes.edges[0].node.children.edges").entityList(CommunicationEventPurposeTypeEdge.class).hasSize(5)
				.path("communicationEventPurposeTypes.edges[0].node.children.edges").entityList(CommunicationEventPurposeTypeEdge.class).contains(children.stream()
						                                                                                                                                  .map(type1 -> CommunicationEventPurposeTypeEntity.builder()
								                                                                                                                                                .description(type1.getDescription())
								                                                                                                                                                .id(type1.getId())
								                                                                                                                                                .build())
						                                                                                                                                  .sorted((type1, type2) ->
								                                                                                                                                          type1.getDescription().compareTo(type2.getDescription()))
						                                                                                                                                  .map(type1 -> CommunicationEventPurposeTypeEdge.builder()
								                                                                                                                                                .node(type1)
								                                                                                                                                                .build())
						                                                                                                                                  .toArray(CommunicationEventPurposeTypeEdge[]::new));

	}


	private CommunicationEventPurposeTypeEntity aCommunicationEventPurposeTypeExists() {
		communicationEventPurposeTypeEntity = communicationEventPurposeTypeRepository.save(CommunicationEventPurposeTypeEntity.builder()
				                                                                             .description("communicationEventPurposeTypeEntity description " + UUID.randomUUID())
				                                                                             .build());
		return communicationEventPurposeTypeEntity;
	}

}
