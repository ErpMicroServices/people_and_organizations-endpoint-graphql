package org.erpmicroservices.peopleandorganizations.endpoint.graphql.facility.controllers;

import org.erpmicroservices.peopleandorganizations.backend.entities.*;
import org.erpmicroservices.peopleandorganizations.backend.repositories.*;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.PageInfo;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.facility.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
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
public class FacilityControllerTest {

    @Mock
    private FacilityRepository facilityRepository;

    @Mock
    private FacilityContactMechanismRepository facilityContactMechanismRepository;

    @Mock
    private FacilityRoleRepository facilityRoleRepository;

    @Mock
    private FacilityTypeRepository facilityTypeRepository;

    @Mock
    private FacilityRoleTypeRepository facilityRoleTypeRepository;

    @Mock
    private PartyRepository partyRepository;

    @Mock
    private PartyTypeRepository partyTypeRepository;

    @Mock
    private ContactMechanismRepository contactMechanismRepository;

    @InjectMocks
    private FacilityController controller;

    private List<FacilityEntity> facilityEntities;
    private List<FacilityContactMechanismEntity> facilityContactMechanismEntities;
    private List<FacilityRoleEntity> facilityRoleEntities;
    private FacilityTypeEntity facilityTypeEntity;
    private FacilityRoleTypeEntity facilityRoleTypeEntity;
    private PartyEntity partyEntity;
    private PartyTypeEntity partyTypeEntity;
    private UUID facilityId;
    private UUID facilityTypeId;
    private UUID facilityRoleId;
    private UUID facilityRoleTypeId;
    private UUID partyId;
    private UUID partyTypeId;
    private PageInfo pageInfo;

    @BeforeEach
    void setUp() {
        // Create test data
        facilityId = UUID.randomUUID();
        facilityTypeId = UUID.randomUUID();
        facilityRoleId = UUID.randomUUID();
        facilityRoleTypeId = UUID.randomUUID();
        partyId = UUID.randomUUID();
        partyTypeId = UUID.randomUUID();

        facilityEntities = new ArrayList<>();
        facilityContactMechanismEntities = new ArrayList<>();
        facilityRoleEntities = new ArrayList<>();

        // Create facility type
        facilityTypeEntity = new FacilityTypeEntity();
        facilityTypeEntity.setId(facilityTypeId);
        facilityTypeEntity.setDescription("Warehouse");

        // Create facility role type
        facilityRoleTypeEntity = new FacilityRoleTypeEntity();
        facilityRoleTypeEntity.setId(facilityRoleTypeId);
        facilityRoleTypeEntity.setDescription("Owner");

        // Create party type
        partyTypeEntity = new PartyTypeEntity();
        partyTypeEntity.setId(partyTypeId);
        partyTypeEntity.setDescription("Organization");

        // Create party
        partyEntity = new PartyEntity();
        partyEntity.setId(partyId);
        partyEntity.setPartyTypeId(partyTypeId);

        // Create facility entities
        for (int i = 0; i < 3; i++) {
            FacilityEntity entity = new FacilityEntity();
            entity.setId(i == 0 ? facilityId : UUID.randomUUID());
            entity.setDescription("Facility " + i);
            entity.setSquareFootage((long) (1000 + i * 100));
            entity.setFacilityTypeId(facilityTypeId);
            entity.setPartOfId(null); // Top-level facilities
            facilityEntities.add(entity);
        }

        // Create facility contact mechanism entities
        for (int i = 0; i < 2; i++) {
            FacilityContactMechanismEntity entity = new FacilityContactMechanismEntity();
            entity.setId(UUID.randomUUID());
            entity.setFacilityId(facilityId);
            entity.setContactMechanismId(UUID.randomUUID());
            entity.setFromDate(LocalDate.now().minusDays(10));
            entity.setThruDate(null);
            facilityContactMechanismEntities.add(entity);
        }

        // Create facility role entities
        for (int i = 0; i < 2; i++) {
            FacilityRoleEntity entity = new FacilityRoleEntity();
            entity.setId(i == 0 ? facilityRoleId : UUID.randomUUID());
            entity.setFacilityId(facilityId);
            entity.setPartyId(partyId);
            entity.setFacilityRoleTypeId(facilityRoleTypeId);
            entity.setFromDate(LocalDate.now().minusDays(10));
            entity.setThruDate(null);
            facilityRoleEntities.add(entity);
        }

        // Create page info
        pageInfo = PageInfo.builder()
                .pageNumber(0)
                .pageSize(10)
                .sortBy(List.of("description"))
                .build();
    }

    @Test
    void facilities_ShouldReturnAllTopLevelFacilities() {
        // Arrange
        Page<FacilityEntity> page = new PageImpl<>(facilityEntities);
        when(facilityRepository.findByPartOfIdIsNull(any(Pageable.class))).thenReturn(page);

        // Act
        FacilityConnection result = controller.facilities(pageInfo);

        // Assert
        assertNotNull(result);
        assertEquals(3, result.getEdges().size());

        // Verify all facilities are returned
        for (int i = 0; i < 3; i++) {
            Facility facility = result.getEdges().get(i).getNode();
            assertEquals(facilityEntities.get(i).getDescription(), facility.getDescription());
            assertEquals(facilityEntities.get(i).getSquareFootage(), facility.getSquareFootage());
        }
    }

    @Test
    void facilityType_ShouldReturnFacilityType() {
        // Arrange
        when(facilityTypeRepository.findById(eq(facilityTypeId))).thenReturn(Optional.of(facilityTypeEntity));

        // Act
        FacilityTypeEntity result = controller.facilityType(facilityEntities.get(0));

        // Assert
        assertNotNull(result);
        assertEquals(facilityTypeId, result.getId());
        assertEquals("Warehouse", result.getDescription());
    }

    @Test
    void contactMechanisms_ShouldReturnContactMechanismsForFacility() {
        // Arrange
        Page<FacilityContactMechanismEntity> page = new PageImpl<>(facilityContactMechanismEntities);
        when(facilityContactMechanismRepository.findByFacilityId(eq(facilityId), any(Pageable.class))).thenReturn(page);

        // Act
        FacilityContactMechanismConnection result = controller.contactMechanisms(pageInfo, facilityEntities.get(0));

        // Assert
        assertNotNull(result);
        assertEquals(2, result.getEdges().size());

        // Verify all contact mechanisms are returned
        for (int i = 0; i < 2; i++) {
            FacilityContactMechanism contactMechanism = result.getEdges().get(i).getNode();
            assertEquals(facilityContactMechanismEntities.get(i).getId(), contactMechanism.getId());
            assertEquals(facilityContactMechanismEntities.get(i).getFromDate(), contactMechanism.getFromDate());
            assertEquals(facilityContactMechanismEntities.get(i).getThruDate(), contactMechanism.getThruData());
        }
    }

    @Test
    void roles_ShouldReturnRolesForFacility() {
        // Arrange
        Page<FacilityRoleEntity> page = new PageImpl<>(facilityRoleEntities);
        when(facilityRoleRepository.findByFacilityId(eq(facilityId), any(Pageable.class))).thenReturn(page);

        // Act
        FacilityRoleConnection result = controller.roles(pageInfo, facilityEntities.get(0));

        // Assert
        assertNotNull(result);
        assertEquals(2, result.getEdges().size());

        // Verify all roles are returned
        for (int i = 0; i < 2; i++) {
            FacilityRole role = result.getEdges().get(i).getNode();
            assertEquals(facilityRoleEntities.get(i).getId(), role.getId());
            assertEquals(facilityRoleEntities.get(i).getFromDate(), role.getFrom());
            assertEquals(facilityRoleEntities.get(i).getThruDate(), role.getThru());
        }
    }

    @Test
    void facilityRoleType_ShouldReturnFacilityRoleType() {
        // Arrange
        when(facilityRoleTypeRepository.findById(eq(facilityRoleTypeId))).thenReturn(Optional.of(facilityRoleTypeEntity));

        // Act
        FacilityRoleTypeEntity result = controller.facilityRoleType(facilityRoleEntities.get(0));

        // Assert
        assertNotNull(result);
        assertEquals(facilityRoleTypeId, result.getId());
        assertEquals("Owner", result.getDescription());
    }

    @Test
    void party_ShouldReturnPartyForFacilityRole() {
        // Arrange
        when(partyRepository.findById(eq(partyId))).thenReturn(Optional.of(partyEntity));

        // Act
        PartyEntity result = controller.party(facilityRoleEntities.get(0));

        // Assert
        assertNotNull(result);
        assertEquals(partyId, result.getId());
        assertEquals(partyTypeId, result.getPartyTypeId());
    }

    @Test
    void facility_ShouldReturnFacilityForFacilityRole() {
        // Arrange
        when(facilityRepository.findById(eq(facilityId))).thenReturn(Optional.of(facilityEntities.get(0)));

        // Act
        FacilityEntity result = controller.facility(facilityRoleEntities.get(0));

        // Assert
        assertNotNull(result);
        assertEquals(facilityId, result.getId());
        assertEquals("Facility 0", result.getDescription());
        assertEquals(1000, result.getSquareFootage());
    }

    @Test
    void partyType_ShouldReturnPartyTypeForParty() {
        // Arrange
        when(partyTypeRepository.findById(eq(partyTypeId))).thenReturn(Optional.of(partyTypeEntity));

        // Act
        PartyTypeEntity result = controller.partyType(partyEntity);

        // Assert
        assertNotNull(result);
        assertEquals(partyTypeId, result.getId());
        assertEquals("Organization", result.getDescription());
    }

    @Test
    void createFacility_ShouldCreateNewFacility() {
        // Arrange
        NewFacility newFacility = new NewFacility();
        newFacility.setDescription("New Facility");
        newFacility.setSquareFootage(2000L);
        newFacility.setFacilityTypeId(facilityTypeId);

        FacilityEntity savedEntity = new FacilityEntity();
        savedEntity.setId(UUID.randomUUID());
        savedEntity.setDescription("New Facility");
        savedEntity.setSquareFootage(2000L);
        savedEntity.setFacilityTypeId(facilityTypeId);

        when(facilityTypeRepository.findById(eq(facilityTypeId))).thenReturn(Optional.of(facilityTypeEntity));
        when(facilityRepository.save(any(FacilityEntity.class))).thenReturn(savedEntity);

        // Act
        FacilityEntity result = controller.createFacility(newFacility);

        // Assert
        assertNotNull(result);
        assertEquals("New Facility", result.getDescription());
        assertEquals(2000, result.getSquareFootage());
        assertEquals(facilityTypeId, result.getFacilityTypeId());
    }

    @Test
    void addFacilityRole_ShouldAddNewFacilityRole() {
        // Arrange
        NewFacilityRole newFacilityRole = new NewFacilityRole();
        newFacilityRole.setFacilityId(facilityId);
        newFacilityRole.setPartyId(partyId);
        newFacilityRole.setFacilityRoleTypeId(facilityRoleTypeId);
        newFacilityRole.setFromDate(LocalDate.now());

        FacilityRoleEntity savedEntity = new FacilityRoleEntity();
        savedEntity.setId(UUID.randomUUID());
        savedEntity.setFacilityId(facilityId);
        savedEntity.setPartyId(partyId);
        savedEntity.setFacilityRoleTypeId(facilityRoleTypeId);
        savedEntity.setFromDate(LocalDate.now());

        when(facilityRoleRepository.save(any(FacilityRoleEntity.class))).thenReturn(savedEntity);

        // Act
        FacilityRoleEntity result = controller.addFacilityRole(newFacilityRole);

        // Assert
        assertNotNull(result);
        assertEquals(facilityId, result.getFacilityId());
        assertEquals(partyId, result.getPartyId());
        assertEquals(facilityRoleTypeId, result.getFacilityRoleTypeId());
    }
}
