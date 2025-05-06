package org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationEvent;

import org.erpmicroservices.peopleandorganizations.backend.entities.CommunicationEventRoleTypeEntity;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.models.CommunicationEventRoleTypeEdge;
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
public class CommunicationEventRoleTypeQueryTest extends CommunicationEventGwtTemplateTemplate {

	private final List<CommunicationEventRoleTypeEntity> children = new ArrayList<>();
	private CommunicationEventRoleTypeEntity parent;

	private CommunicationEventRoleTypeEntity communicationEventRoleTypeEntity;

	@BeforeEach
	@Override
	public void given() {
		super.given();
		aCommunicationEventRoleTypeWithChildrenExists(5);
	}

	private void aCommunicationEventRoleTypeWithChildrenExists(final int numberOfChildren) {
		parent = aCommunicationEventRoleTypeExists();
		for (int i = 0; i < numberOfChildren; i++) {
			CommunicationEventRoleTypeEntity child = aCommunicationEventRoleTypeExists();
			child.setParentId(parent.getId());
			child = communicationEventRoleTypeRepository.save(child);
			children.add(child);
		}
	}

	@Test
	@Override
	public void when() {
		response = this.graphQlTester.documentName("CommunicationEventRoleTypeQuery")
				           .operationName("CommunicationEventRoleTypeQuery")
				           .variable("childrenPageInfo", pageInfoSortingOn("description"))
				           .execute();
	}

	@AfterEach
	@Override
	public void then() {
		response
				.path("communicationEventRoleTypes.edges").entityList(CommunicationEventRoleTypeEntity.class).hasSize(1)
				.path("communicationEventRoleTypes.edges[0].node.id").entity(UUID.class).isEqualTo(parent.getId())
				.path("communicationEventRoleTypes.edges[0].node.description").entity(String.class).isEqualTo(parent.getDescription())
				.path("communicationEventRoleTypes.edges[0].node.parent").valueIsNull()
				.path("communicationEventRoleTypes.edges[0].node.children.edges").entityList(CommunicationEventRoleTypeEdge.class).hasSize(5)
				.path("communicationEventRoleTypes.edges[0].node.children.edges").entityList(CommunicationEventRoleTypeEdge.class).contains(children.stream()
						                                                                                                                            .map(type1 -> org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.models.CommunicationEventRoleType.builder()
								                                                                                                                                          .description(type1.getDescription())
								                                                                                                                                          .id(type1.getId())
								                                                                                                                                          .build())
						                                                                                                                            .sorted((type1, type2) ->
								                                                                                                                                    type1.getDescription().compareTo(type2.getDescription()))
						                                                                                                                            .map(type1 -> CommunicationEventRoleTypeEdge.builder()
								                                                                                                                                          .node(type1)
								                                                                                                                                          .build())
						                                                                                                                            .toArray(CommunicationEventRoleTypeEdge[]::new));

	}


	private CommunicationEventRoleTypeEntity aCommunicationEventRoleTypeExists() {
		communicationEventRoleTypeEntity = communicationEventRoleTypeRepository.save(CommunicationEventRoleTypeEntity.builder()
				                                                                       .description("communicationEventRoleTypeEntity description " + UUID.randomUUID())
				                                                                       .build());
		return communicationEventRoleTypeEntity;
	}

}
