package org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationEvent.controllers;

import org.erpmicroservices.peopleandorganizations.backend.entities.CommunicationEventPurposeTypeEntity;
import org.erpmicroservices.peopleandorganizations.backend.repositories.CommunicationEventPurposeTypeRepository;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.controllers.CommunicationEventPurposeTypeController;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.models.CommunicationEventPurposeType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.models.CommunicationEventPurposeTypeConnection;
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
public class CommunicationEventPurposeTypeControllerTest {

    @Mock
    private CommunicationEventPurposeTypeRepository communicationEventPurposeTypeRepository;

    @InjectMocks
    private CommunicationEventPurposeTypeController controller;

    private List<CommunicationEventPurposeTypeEntity> parentEntities;
    private List<CommunicationEventPurposeTypeEntity> childEntities;
    private UUID parentId;
    private PageInfo pageInfo;

    @BeforeEach
    void setUp() {
        // Create test data
        parentId = UUID.randomUUID();
        parentEntities = new ArrayList<>();
        childEntities = new ArrayList<>();

        // Create a parent entity
        CommunicationEventPurposeTypeEntity parent = new CommunicationEventPurposeTypeEntity(
                parentId,
                "Parent Purpose Type",
                null
        );
        parentEntities.add(parent);

        // Create child entities
        for (int i = 0; i < 5; i++) {
            CommunicationEventPurposeTypeEntity child = new CommunicationEventPurposeTypeEntity(
                    UUID.randomUUID(),
                    "Child Purpose Type " + i,
                    parentId
            );
            childEntities.add(child);
        }

        // Create page info
        pageInfo = PageInfo.builder()
                .pageNumber(0)
                .pageSize(10)
                .build();
    }

    @Test
    void communicationEventPurposeTypes_ShouldReturnParentTypes() {
        // Arrange
        Page<CommunicationEventPurposeTypeEntity> page = new PageImpl<>(parentEntities);
        when(communicationEventPurposeTypeRepository.findCommunicationEventPurposeTypeByParentIdIsNull(any(Pageable.class)))
                .thenReturn(page);

        // Act
        CommunicationEventPurposeTypeConnection result = controller.communicationEventPurposeTypes(pageInfo);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getEdges().size());
        assertEquals(parentId, result.getEdges().get(0).getNode().getId());
        assertEquals("Parent Purpose Type", result.getEdges().get(0).getNode().getDescription());
    }

    @Test
    void children_ShouldReturnChildTypes() {
        // Arrange
        Page<CommunicationEventPurposeTypeEntity> page = new PageImpl<>(childEntities);
        when(communicationEventPurposeTypeRepository.findCommunicationEventPurposeTypeByParentId(eq(parentId), any(Pageable.class)))
                .thenReturn(page);

        // Act
        CommunicationEventPurposeTypeConnection result = controller.children(pageInfo, parentEntities.get(0));

        // Assert
        assertNotNull(result);
        assertEquals(5, result.getEdges().size());

        // Verify all child types are returned
        for (int i = 0; i < 5; i++) {
            CommunicationEventPurposeType node = result.getEdges().get(i).getNode();
            assertEquals(childEntities.get(i).getId(), node.getId());
            assertEquals(childEntities.get(i).getDescription(), node.getDescription());
        }
    }
}
