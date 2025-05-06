package org.erpmicroservices.peopleandorganizations.endpoint.graphql.facility.controllers;

import org.erpmicroservices.peopleandorganizations.backend.entities.FacilityRoleTypeEntity;
import org.erpmicroservices.peopleandorganizations.backend.repositories.FacilityRoleTypeRepository;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.PageInfo;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.facility.models.FacilityRoleType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.facility.models.FacilityRoleTypeConnection;
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
public class FacilityRoleTypeControllerTest {

    @Mock
    private FacilityRoleTypeRepository facilityRoleTypeRepository;

    @InjectMocks
    private FacilityRoleTypeController controller;

    private List<FacilityRoleTypeEntity> parentEntities;
    private List<FacilityRoleTypeEntity> childEntities;
    private UUID parentId;
    private PageInfo pageInfo;

    @BeforeEach
    void setUp() {
        // Create test data
        parentId = UUID.randomUUID();
        parentEntities = new ArrayList<>();
        childEntities = new ArrayList<>();

        // Create a parent entity
        FacilityRoleTypeEntity parent = new FacilityRoleTypeEntity();
        parent.setId(parentId);
        parent.setDescription("Parent Role Type");
        parent.setParentId(null);
        parentEntities.add(parent);

        // Create child entities
        for (int i = 0; i < 5; i++) {
            FacilityRoleTypeEntity child = new FacilityRoleTypeEntity();
            child.setId(UUID.randomUUID());
            child.setDescription("Child Role Type " + i);
            child.setParentId(parentId);
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
    void facilityRoleTypes_ShouldReturnParentTypes() {
        // Arrange
        Page<FacilityRoleTypeEntity> page = new PageImpl<>(parentEntities);
        when(facilityRoleTypeRepository.findFacilityRoleTypesByParentIdIsNull(any(Pageable.class)))
                .thenReturn(page);

        // Act
        FacilityRoleTypeConnection result = controller.facilityRoleTypes(pageInfo);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getEdges().size());
        assertEquals(parentId, result.getEdges().get(0).getNode().getId());
        assertEquals("Parent Role Type", result.getEdges().get(0).getNode().getDescription());
    }

    @Test
    void children_ShouldReturnChildTypes() {
        // Arrange
        Page<FacilityRoleTypeEntity> page = new PageImpl<>(childEntities);
        when(facilityRoleTypeRepository.findFacilityRoleTypesByParentId(eq(parentId), any(Pageable.class)))
                .thenReturn(page);

        // Act
        FacilityRoleTypeConnection result = controller.children(parentEntities.get(0), pageInfo);

        // Assert
        assertNotNull(result);
        assertEquals(5, result.getEdges().size());

        // Verify all child types are returned
        for (int i = 0; i < 5; i++) {
            FacilityRoleType node = result.getEdges().get(i).getNode();
            assertEquals(childEntities.get(i).getId(), node.getId());
            assertEquals(childEntities.get(i).getDescription(), node.getDescription());
        }
    }
}
