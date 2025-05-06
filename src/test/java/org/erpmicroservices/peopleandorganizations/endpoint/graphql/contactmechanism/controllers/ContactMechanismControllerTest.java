package org.erpmicroservices.peopleandorganizations.endpoint.graphql.contactmechanism.controllers;

import org.erpmicroservices.peopleandorganizations.backend.entities.ContactMechanismEntity;
import org.erpmicroservices.peopleandorganizations.backend.entities.ContactMechanismTypeEntity;
import org.erpmicroservices.peopleandorganizations.backend.entities.GeographicBoundaryEntity;
import org.erpmicroservices.peopleandorganizations.backend.repositories.ContactMechanismGeographicBoundaryRepository;
import org.erpmicroservices.peopleandorganizations.backend.repositories.ContactMechanismRepository;
import org.erpmicroservices.peopleandorganizations.backend.repositories.ContactMechanismTypeRepository;
import org.erpmicroservices.peopleandorganizations.backend.repositories.GeographicBoundaryRepository;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.contactmechanism.models.ContactMechanism;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.contactmechanism.models.ContactMechanismConnection;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.contactmechanism.models.ContactMechanismType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.PageInfo;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.facility.models.FacilityContactMechanism;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.geographicboundary.GeographicBoundaryConnection;
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
public class ContactMechanismControllerTest {

    @Mock
    private ContactMechanismRepository contactMechanismRepository;

    @Mock
    private ContactMechanismGeographicBoundaryRepository contactMechanismGeographicBoundaryRepository;

    @Mock
    private ContactMechanismTypeRepository contactMechanismTypeRepository;

    @Mock
    private GeographicBoundaryRepository geographicBoundaryRepository;

    @InjectMocks
    private ContactMechanismController controller;

    private List<ContactMechanismEntity> contactMechanismEntities;
    private List<GeographicBoundaryEntity> geographicBoundaryEntities;
    private ContactMechanismTypeEntity contactMechanismTypeEntity;
    private UUID contactMechanismId;
    private UUID contactMechanismTypeId;
    private PageInfo pageInfo;

    @BeforeEach
    void setUp() {
        // Create test data
        contactMechanismId = UUID.randomUUID();
        contactMechanismTypeId = UUID.randomUUID();
        contactMechanismEntities = new ArrayList<>();
        geographicBoundaryEntities = new ArrayList<>();

        // Create contact mechanism type
        contactMechanismTypeEntity = new ContactMechanismTypeEntity();
        contactMechanismTypeEntity.setId(contactMechanismTypeId);
        contactMechanismTypeEntity.setDescription("Email");

        // Create contact mechanism entities
        for (int i = 0; i < 3; i++) {
            ContactMechanismEntity entity = new ContactMechanismEntity();
            entity.setId(i == 0 ? contactMechanismId : UUID.randomUUID());
            entity.setEndPoint("test" + i + "@example.com");
            entity.setDirections("Test directions " + i);
            entity.setContactMechanismTypeId(contactMechanismTypeId);
            contactMechanismEntities.add(entity);
        }

        // Create geographic boundary entities
        for (int i = 0; i < 2; i++) {
            GeographicBoundaryEntity entity = new GeographicBoundaryEntity();
            entity.setId(UUID.randomUUID());
            entity.setName("Geographic Boundary " + i);
            entity.setGeoCode("GB" + i);
            entity.setAbbreviation("GB" + i);
            geographicBoundaryEntities.add(entity);
        }

        // Create page info
        pageInfo = PageInfo.builder()
                .pageNumber(0)
                .pageSize(10)
                .sortBy(List.of("endPoint"))
                .build();
    }

    @Test
    void contactMechanisms_ShouldReturnAllContactMechanisms() {
        // Arrange
        Page<ContactMechanismEntity> page = new PageImpl<>(contactMechanismEntities);
        when(contactMechanismRepository.findAll(any(Pageable.class))).thenReturn(page);
        when(contactMechanismTypeRepository.findById(eq(contactMechanismTypeId)))
                .thenReturn(Optional.of(contactMechanismTypeEntity));

        // Act
        ContactMechanismConnection result = controller.contactMechanisms(pageInfo);

        // Assert
        assertNotNull(result);
        assertEquals(3, result.getEdges().size());

        // Verify all contact mechanisms are returned
        for (int i = 0; i < 3; i++) {
            ContactMechanism node = result.getEdges().get(i).getNode();
            assertEquals(contactMechanismEntities.get(i).getId(), node.getId());
            assertEquals(contactMechanismEntities.get(i).getEndPoint(), node.getEndPoint());
            assertEquals(contactMechanismEntities.get(i).getDirections(), node.getDirections());
            assertNotNull(node.getContactMechanismType());
            assertEquals(contactMechanismTypeId, node.getContactMechanismType().getId());
            assertEquals("Email", node.getContactMechanismType().getDescription());
        }
    }

    @Test
    void facilityContactMechanism_ShouldReturnContactMechanism() {
        // Arrange
        ContactMechanismEntity entity = contactMechanismEntities.get(0);
        when(contactMechanismRepository.findById(eq(contactMechanismId))).thenReturn(Optional.of(entity));
        when(contactMechanismTypeRepository.findById(eq(contactMechanismTypeId)))
                .thenReturn(Optional.of(contactMechanismTypeEntity));

        // Create facility contact mechanism
        ContactMechanism contactMechanism = ContactMechanism.builder()
                .id(contactMechanismId)
                .build();
        FacilityContactMechanism facilityContactMechanism = FacilityContactMechanism.builder()
                .id(UUID.randomUUID())
                .contactMechanism(contactMechanism)
                .build();

        // Act
        ContactMechanism result = controller.facilityContactMechanism(facilityContactMechanism);

        // Assert
        assertNotNull(result);
        assertEquals(contactMechanismId, result.getId());
        assertEquals(entity.getEndPoint(), result.getEndPoint());
        assertEquals(entity.getDirections(), result.getDirections());
        assertNotNull(result.getContactMechanismType());
        assertEquals(contactMechanismTypeId, result.getContactMechanismType().getId());
        assertEquals("Email", result.getContactMechanismType().getDescription());
    }

    @Test
    void geographicBoundaries_ShouldReturnGeographicBoundariesForContactMechanism() {
        // Arrange
        Page<GeographicBoundaryEntity> page = new PageImpl<>(geographicBoundaryEntities);
        when(contactMechanismGeographicBoundaryRepository.findGeographicBoundaryByContactMechanismId(
                eq(contactMechanismId), any(Pageable.class))).thenReturn(page);

        // Create contact mechanism
        ContactMechanism contactMechanism = ContactMechanism.builder()
                .id(contactMechanismId)
                .build();

        // Act
        GeographicBoundaryConnection result = controller.geographicBoundaries(pageInfo, contactMechanism);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.getEdges().size());

        // Verify all geographic boundaries are returned
        for (int i = 0; i < 2; i++) {
            assertEquals(geographicBoundaryEntities.get(i).getId(), result.getEdges().get(i).getNode().getId());
            assertEquals(geographicBoundaryEntities.get(i).getName(), result.getEdges().get(i).getNode().getName());
            assertEquals(geographicBoundaryEntities.get(i).getGeoCode(), result.getEdges().get(i).getNode().getGeoCode());
            assertEquals(geographicBoundaryEntities.get(i).getAbbreviation(), result.getEdges().get(i).getNode().getAbbreviation());
        }
    }

    @Test
    void contactMechanismType_ShouldReturnContactMechanismType() {
        // Arrange
        when(contactMechanismTypeRepository.findById(eq(contactMechanismTypeId)))
                .thenReturn(Optional.of(contactMechanismTypeEntity));

        // Create contact mechanism
        ContactMechanism contactMechanism = ContactMechanism.builder()
                .id(contactMechanismId)
                .contactMechanismType(ContactMechanismType.builder()
                        .id(contactMechanismTypeId)
                        .build())
                .build();

        // Act
        ContactMechanismType result = controller.contactMechanismType(contactMechanism);

        // Assert
        assertNotNull(result);
        assertEquals(contactMechanismTypeId, result.getId());
        assertEquals("Email", result.getDescription());
    }
}
