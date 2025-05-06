package org.erpmicroservices.peopleandorganizations.endpoint.graphql.contactmechanism.controllers;

import org.erpmicroservices.peopleandorganizations.backend.entities.CommunicationEventRoleTypeEntity;
import org.erpmicroservices.peopleandorganizations.backend.entities.ContactMechanismTypeEntity;
import org.erpmicroservices.peopleandorganizations.backend.entities.ValidContactMechanismRoleEntity;
import org.erpmicroservices.peopleandorganizations.backend.repositories.CommunicationEventRoleTypeRepository;
import org.erpmicroservices.peopleandorganizations.backend.repositories.ContactMechanismTypeRepository;
import org.erpmicroservices.peopleandorganizations.backend.repositories.ValidContactMechanismRoleRepository;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.models.CommunicationEventRoleType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.contactmechanism.models.ContactMechanismType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.contactmechanism.models.ValidContactMechanismRole;
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
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ValidContactMechanismRoleControllerTest {

    @Mock
    private ValidContactMechanismRoleRepository validContactMechanismRoleRepository;

    @Mock
    private ContactMechanismTypeRepository contactMechanismTypeRepository;

    @Mock
    private CommunicationEventRoleTypeRepository communicationEventRoleTypeRepository;

    @InjectMocks
    private ValidContactMechanismRoleController controller;

    private List<ValidContactMechanismRoleEntity> validContactMechanismRoleEntities;
    private ContactMechanismTypeEntity contactMechanismTypeEntity;
    private CommunicationEventRoleTypeEntity communicationEventRoleTypeEntity;
    private UUID contactMechanismTypeId;
    private UUID communicationEventRoleTypeId;
    private PageInfo pageInfo;

    @BeforeEach
    void setUp() {
        // Create test data
        contactMechanismTypeId = UUID.randomUUID();
        communicationEventRoleTypeId = UUID.randomUUID();
        validContactMechanismRoleEntities = new ArrayList<>();

        // Create contact mechanism type
        contactMechanismTypeEntity = new ContactMechanismTypeEntity();
        contactMechanismTypeEntity.setId(contactMechanismTypeId);
        contactMechanismTypeEntity.setDescription("Email");

        // Create communication event role type
        communicationEventRoleTypeEntity = new CommunicationEventRoleTypeEntity(
                communicationEventRoleTypeId,
                "Sender",
                null
        );

        // Create valid contact mechanism role entities
        for (int i = 0; i < 3; i++) {
            ValidContactMechanismRoleEntity entity = new ValidContactMechanismRoleEntity();
            entity.setId(UUID.randomUUID());
            entity.setContactMechanismTypeId(contactMechanismTypeId);
            entity.setCommunicationEventRoleTypeId(communicationEventRoleTypeId);
            validContactMechanismRoleEntities.add(entity);
        }

        // Create page info
        pageInfo = PageInfo.builder()
                .pageNumber(0)
                .pageSize(10)
                .sortBy(List.of("id"))
                .build();
    }

    @Test
    void validContactMechanismRoles_ShouldReturnAllValidContactMechanismRoles() {
        // Arrange
        Page<ValidContactMechanismRoleEntity> page = new PageImpl<>(validContactMechanismRoleEntities);
        when(validContactMechanismRoleRepository.findAll(any(Pageable.class))).thenReturn(page);
        when(contactMechanismTypeRepository.findById(eq(contactMechanismTypeId)))
                .thenReturn(Optional.of(contactMechanismTypeEntity));
        when(communicationEventRoleTypeRepository.findById(eq(communicationEventRoleTypeId)))
                .thenReturn(Optional.of(communicationEventRoleTypeEntity));

        // Act
        List<ValidContactMechanismRole> result = controller.validContactMechanismRoles(pageInfo);

        // Assert
        assertNotNull(result);
        assertEquals(3, result.size());

        // Verify all valid contact mechanism roles are returned
        for (int i = 0; i < 3; i++) {
            ValidContactMechanismRole role = result.get(i);
            assertEquals(validContactMechanismRoleEntities.get(i).getId(), role.getId());

            // Verify contact mechanism type
            assertNotNull(role.getContactMechanismType());
            assertEquals(contactMechanismTypeId, role.getContactMechanismType().getId());
            assertEquals("Email", role.getContactMechanismType().getDescription());

            // Verify communication event role type
            assertNotNull(role.getCommunicationEventRoleType());
            assertEquals(communicationEventRoleTypeId, role.getCommunicationEventRoleType().getId());
            assertEquals("Sender", role.getCommunicationEventRoleType().getDescription());
        }
    }
}
