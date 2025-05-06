package org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationEvent.controllers;

import org.erpmicroservices.peopleandorganizations.backend.entities.CommunicationEventWorkEffortEntity;
import org.erpmicroservices.peopleandorganizations.backend.repositories.CommunicationEventRepository;
import org.erpmicroservices.peopleandorganizations.backend.repositories.CommunicationEventWorkEffortRepository;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.controllers.CommunicationEventWorkEffortController;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.models.CommunicationEvent;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.models.CommunicationEventWorkEffort;
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
public class CommunicationEventWorkEffortControllerTest {

    @Mock
    private CommunicationEventWorkEffortRepository communicationEventWorkEffortRepository;

    @Mock
    private CommunicationEventRepository communicationEventRepository;

    @InjectMocks
    private CommunicationEventWorkEffortController controller;

    private List<CommunicationEventWorkEffortEntity> workEffortEntities;
    private CommunicationEvent communicationEvent;
    private UUID communicationEventId;
    private PageInfo pageInfo;

    @BeforeEach
    void setUp() {
        // Create test data
        communicationEventId = UUID.randomUUID();
        workEffortEntities = new ArrayList<>();

        // Create communication event
        communicationEvent = CommunicationEvent.builder()
                .id(communicationEventId)
                .started(ZonedDateTime.now())
                .ended(ZonedDateTime.now().plusHours(1))
                .note("Test Communication Event")
                .build();

        // Create work effort entities
        for (int i = 0; i < 3; i++) {
            CommunicationEventWorkEffortEntity entity = new CommunicationEventWorkEffortEntity();
            entity.setId(UUID.randomUUID());
            entity.setCommunicationEventId(communicationEventId);
            entity.setWorkEffortId(UUID.randomUUID());
            workEffortEntities.add(entity);
        }

        // Create page info
        pageInfo = PageInfo.builder()
                .pageNumber(0)
                .pageSize(10)
                .sortBy(List.of("id"))
                .build();
    }

    @Test
    void workEfforts_ShouldReturnWorkEffortsForCommunicationEvent() {
        // Arrange
        Page<CommunicationEventWorkEffortEntity> page = new PageImpl<>(workEffortEntities);
        when(communicationEventWorkEffortRepository.findByCommunicationEventId(eq(communicationEventId), any(Pageable.class)))
                .thenReturn(page);

        // Act
        List<CommunicationEventWorkEffort> result = controller.workEfforts(pageInfo, communicationEvent);

        // Assert
        assertNotNull(result);
        assertEquals(3, result.size());

        // Verify all work efforts are returned
        for (int i = 0; i < 3; i++) {
            CommunicationEventWorkEffort workEffort = result.get(i);
            assertEquals(workEffortEntities.get(i).getId(), workEffort.getId());
            assertEquals(workEffortEntities.get(i).getWorkEffortId(), workEffort.getWorkEffortId());
            assertEquals(communicationEvent, workEffort.getCommunicationEvent());
        }
    }
}
