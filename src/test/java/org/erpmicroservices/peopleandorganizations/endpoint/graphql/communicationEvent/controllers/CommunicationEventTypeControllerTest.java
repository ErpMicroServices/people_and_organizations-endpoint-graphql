package org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationEvent.controllers;

import org.erpmicroservices.peopleandorganizations.backend.entities.CommunicationEventTypeEntity;
import org.erpmicroservices.peopleandorganizations.backend.repositories.CommunicationEventTypeRepository;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.controllers.CommunicationEventTypeController;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.models.CommunicationEventType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.models.CommunicationEventTypeConnection;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.PageInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommunicationEventTypeControllerTest {

    @Mock
    private CommunicationEventTypeRepository communicationEventTypeRepository;

    @InjectMocks
    private CommunicationEventTypeController controller;

    private List<CommunicationEventTypeEntity> parentEntities;
    private List<CommunicationEventTypeEntity> childEntities;
    private UUID parentId;
    private PageInfo pageInfo;

    @BeforeEach
    void setUp() {
        // Create test data
        parentId = UUID.randomUUID();
        parentEntities = new ArrayList<>();
        childEntities = new ArrayList<>();

        // Create a parent entity
        CommunicationEventTypeEntity parent = new CommunicationEventTypeEntity(
                parentId,
                "Parent Event Type",
                null
        );
        parentEntities.add(parent);

        // Create child entities
        for (int i = 0; i < 5; i++) {
            CommunicationEventTypeEntity child = new CommunicationEventTypeEntity(
                    UUID.randomUUID(),
                    "Child Event Type " + i,
                    parentId
            );
            childEntities.add(child);
        }

        // Create page info
        pageInfo = PageInfo.builder()
                .pageNumber(0)
                .pageSize(10)
                .sortBy(List.of("description"))
                .build();
    }

    @Test
    void communicationEventTypes_ShouldReturnParentTypes() {
        // Arrange
        Page<CommunicationEventTypeEntity> page = new PageImpl<>(parentEntities);
        when(communicationEventTypeRepository.findCommunicationEventTypeByParentIdIsNull(any(Pageable.class)))
                .thenReturn(page);

        // Act
        CommunicationEventTypeConnection result = controller.communicationEventTypes(pageInfo);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getEdges().size());
        assertEquals(parentId, result.getEdges().get(0).getNode().getId());
        assertEquals("Parent Event Type", result.getEdges().get(0).getNode().getDescription());
    }

    @Test
    void children_ShouldReturnChildTypes() {
        // Arrange
        Page<CommunicationEventTypeEntity> page = new PageImpl<>(childEntities);
        when(communicationEventTypeRepository.findCommunicationEventTypeByParentId(eq(parentId), any(Pageable.class)))
                .thenReturn(page);

        // Act
        CommunicationEventTypeConnection result = controller.children(pageInfo, parentEntities.get(0));

        // Assert
        assertNotNull(result);
        assertEquals(5, result.getEdges().size());

        // Verify all child types are returned
        for (int i = 0; i < 5; i++) {
            CommunicationEventType node = result.getEdges().get(i).getNode();
            assertEquals(childEntities.get(i).getId(), node.getId());
            assertEquals(childEntities.get(i).getDescription(), node.getDescription());
        }
    }
}
