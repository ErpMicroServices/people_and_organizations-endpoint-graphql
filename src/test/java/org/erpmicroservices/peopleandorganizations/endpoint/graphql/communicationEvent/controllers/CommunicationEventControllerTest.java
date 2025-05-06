package org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationEvent.controllers;

import graphql.relay.Edge;
import org.erpmicroservices.peopleandorganizations.backend.entities.CaseEntity;
import org.erpmicroservices.peopleandorganizations.backend.entities.CommunicationEventEntity;
import org.erpmicroservices.peopleandorganizations.backend.repositories.*;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.controllers.CommunicationEventController;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.models.CommunicationEvent;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.models.CommunicationEventConnection;
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

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommunicationEventControllerTest {

    @Mock
    private CommunicationEventRepository communicationEventRepository;

    @Mock
    private CommunicationEventRoleRepository communicationEventRoleRepository;

    @Mock
    private ContactMechanismTypeRepository contactMechanismTypeRepository;

    @Mock
    private CommunicationEventStatusTypeRepository communicationEventStatusTypeRepository;

    @Mock
    private CommunicationEventTypeRepository communicationEventTypeRepository;

    @Mock
    private PartyRelationshipRepository partyRelationshipRepository;

    @InjectMocks
    private CommunicationEventController controller;

    private List<CommunicationEventEntity> communicationEventEntities;
    private CaseEntity caseEntity;
    private UUID caseId;
    private PageInfo pageInfo;

    @BeforeEach
    void setUp() {
        // Create test data
        caseId = UUID.randomUUID();
        communicationEventEntities = new ArrayList<>();

        // Create a case entity
        caseEntity = new CaseEntity();
        caseEntity.setId(caseId);
        caseEntity.setDescription("Test Case");

        // Create communication event entities
        for (int i = 0; i < 3; i++) {
            CommunicationEventEntity entity = new CommunicationEventEntity();
            entity.setId(UUID.randomUUID());
            entity.setStarted(ZonedDateTime.now());
            entity.setEnded(ZonedDateTime.now().plusHours(1));
            entity.setNote("Communication Event Note " + i);
            entity.setCaseId(caseId);
            entity.setCommunicationEventStatusTypeId(UUID.randomUUID());
            entity.setCommunicationEventTypeId(UUID.randomUUID());
            entity.setContactMechanismTypeId(UUID.randomUUID());
            communicationEventEntities.add(entity);
        }

        // Create page info
        pageInfo = PageInfo.builder()
                .pageNumber(0)
                .pageSize(10)
                .sortBy(List.of("started"))
                .build();
    }

    @Test
    void communicationEvents_ShouldReturnCommunicationEventsForCase() {
        // Arrange
        when(communicationEventRepository.findByCaseId(eq(caseId), any(Pageable.class)))
                .thenReturn(communicationEventEntities);

        // Act
        CommunicationEventConnection result = controller.communicationEvents(pageInfo, caseEntity);

        // Assert
        assertNotNull(result);
        assertEquals(3, result.getEdges().size());

        // Verify all communication events are returned
        for (int i = 0; i < 3; i++) {
            CommunicationEvent node = result.getEdges().get(i).getNode();
            assertEquals(communicationEventEntities.get(i).getId(), node.getId());
            assertEquals(communicationEventEntities.get(i).getStarted(), node.getStarted());
            assertEquals(communicationEventEntities.get(i).getEnded(), node.getEnded());
            assertEquals(communicationEventEntities.get(i).getNote(), node.getNote());
        }
    }
}
