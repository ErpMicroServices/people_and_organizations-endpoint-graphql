package org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationEvent.controllers;

import org.erpmicroservices.peopleandorganizations.backend.entities.CommunicationEventRoleTypeEntity;
import org.erpmicroservices.peopleandorganizations.backend.repositories.CommunicationEventRoleTypeRepository;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.controllers.CommunicationEventRoleTypeController;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.models.CommunicationEventRoleType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.models.CommunicationEventRoleTypeConnection;
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

import static graphql.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommunicationEventRoleTypeControllerTest {

    @Mock
    private CommunicationEventRoleTypeRepository communicationEventRoleTypeRepository;

    @InjectMocks
    private CommunicationEventRoleTypeController controller;

    private List<CommunicationEventRoleTypeEntity> parentEntities;
    private List<CommunicationEventRoleTypeEntity> childEntities;
    private UUID parentId;
    private PageInfo pageInfo;

    @BeforeEach
    void setUp() {
        // Create test data
        parentId = UUID.randomUUID();
        parentEntities = new ArrayList<>();
        childEntities = new ArrayList<>();

        // Create a parent entity
        CommunicationEventRoleTypeEntity parent = new CommunicationEventRoleTypeEntity(
                parentId,
                "Parent Role Type",
                null
        );
        parentEntities.add(parent);

        // Create child entities
        for (int i = 0; i < 5; i++) {
            CommunicationEventRoleTypeEntity child = new CommunicationEventRoleTypeEntity(
                    UUID.randomUUID(),
                    "Child Role Type " + i,
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
    void communicationEventRoleTypes_ShouldReturnParentTypes() {
        // Arrange
        Page<CommunicationEventRoleTypeEntity> page = new PageImpl<>(parentEntities);
        when(communicationEventRoleTypeRepository.findCommunicationEventRoleTypeByParentIdIsNull(any(Pageable.class)))
                .thenReturn(page);

        // Act
        CommunicationEventRoleTypeConnection result = controller.communicationEventRoleTypes(pageInfo);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getEdges().size());
        assertEquals(parentId, result.getEdges().get(0).getNode().getId());
        assertEquals("Parent Role Type", result.getEdges().get(0).getNode().getDescription());
    }

    @Test
    void children_ShouldReturnChildTypes() {
        // Arrange
        Page<CommunicationEventRoleTypeEntity> page = new PageImpl<>(childEntities);
        when(communicationEventRoleTypeRepository.findCommunicationEventRoleTypeByParentId(eq(parentId), any(Pageable.class)))
                .thenReturn(page);

        // Act
        CommunicationEventRoleTypeConnection result = controller.children(pageInfo, parentEntities.get(0));

        // Assert
        assertNotNull(result);
        assertEquals(5, result.getEdges().size());

        // Verify all child types are returned
        for (int i = 0; i < 5; i++) {
            CommunicationEventRoleType node = result.getEdges().get(i).getNode();
            assertEquals(childEntities.get(i).getId(), node.getId());
            assertEquals(childEntities.get(i).getDescription(), node.getDescription());
        }
    }
}
