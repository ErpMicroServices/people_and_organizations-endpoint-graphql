package org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationEvent;

import org.erpmicroservices.peopleandorganizations.backend.entities.CommunicationEventStatusTypeEntity;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.models.CommunicationEventStatusTypeEdge;
import org.erpmicroservices.peopleandorganizations.backend.entities.AbstractType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@AutoConfigureGraphQlTester
public class CommunicationEventStatusTypeQueryTest extends CommunicationEventGwtTemplateTemplate {

	private final List<CommunicationEventStatusTypeEntity> children = new ArrayList<>();
	private CommunicationEventStatusTypeEntity parent;

	private CommunicationEventStatusTypeEntity communicationEventStatusTypeEntity;

	@BeforeEach
	@Override
	public void given() {
		super.given();
		aCommunicationEventStatusTypeWithChildrenExists(5);
	}

	@Test
	@Override
	public void when() {
		response = this.graphQlTester.documentName("CommunicationEventStatusTypeQuery")
				           .operationName("CommunicationEventStatusTypeQuery")
				           .variable("childrenPageInfo", pageInfoSortingOn("description"))
				           .execute();
	}

	@AfterEach
	@Override
	public void then() {
		response
				.path("communicationEventStatusTypes.edges").entityList(CommunicationEventStatusTypeEntity.class).hasSize(1)
				.path("communicationEventStatusTypes.edges[0].node.id").entity(UUID.class).isEqualTo(parent.getId())
				.path("communicationEventStatusTypes.edges[0].node.description").entity(String.class).isEqualTo(parent.getDescription())
				.path("communicationEventStatusTypes.edges[0].node.parent").valueIsNull()
				.path("communicationEventStatusTypes.edges[0].node.children.edges").entityList(CommunicationEventStatusTypeEdge.class).hasSize(5)
				.path("communicationEventStatusTypes.edges[0].node.children.edges").entityList(CommunicationEventStatusTypeEdge.class).contains(children.stream()
						                                                                                                                                .map(type1 -> org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.models.CommunicationEventStatusType.builder()
								                                                                                                                                              .description(type1.getDescription())
								                                                                                                                                              .id(type1.getId())
								                                                                                                                                              .build())
						                                                                                                                                .sorted(Comparator.comparing(type -> type.getDescription()))
						                                                                                                                                .map(type1 -> CommunicationEventStatusTypeEdge.builder()
								                                                                                                                                              .node(type1)
								                                                                                                                                              .build())
						                                                                                                                                .toArray(CommunicationEventStatusTypeEdge[]::new));

	}

	private void aCommunicationEventStatusTypeWithChildrenExists(final int numberOfChildren) {
		parent = aCommunicationEventStatusTypeExists();
		for (int i = 0; i < numberOfChildren; i++) {
			CommunicationEventStatusTypeEntity child = aCommunicationEventStatusTypeExists();
			child.setParentId(parent.getId());
			child = communicationEventStatusTypeRepository.save(child);
			children.add(child);
		}
	}

	private CommunicationEventStatusTypeEntity aCommunicationEventStatusTypeExists() {
		communicationEventStatusTypeEntity = communicationEventStatusTypeRepository.save(CommunicationEventStatusTypeEntity.builder()
				                                                                           .description("CommunicationEventStatusTypeEntity description " + UUID.randomUUID())
				                                                                           .build());
		return communicationEventStatusTypeEntity;
	}
}
