package org.erpmicroservices.peopleandorganizations.endpoint.graphql.facility.controllers;

import graphql.relay.Edge;
import jakarta.validation.constraints.NotNull;
import org.erpmicroservices.peopleandorganizations.backend.entities.*;
import org.erpmicroservices.peopleandorganizations.backend.repositories.*;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.Cursor;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.PageInfo;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.facility.models.*;
import org.springframework.data.domain.Page;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.lang.String.valueOf;
import static org.erpmicroservices.peopleandorganizations.endpoint.graphql.misc.Utils.pageInfoToPageable;

@Controller
public class FacilityController {

	private final FacilityRepository facilityRepository;
	private final FacilityContactMechanismRepository facilityContactMechanismRepository;

	private final FacilityRoleRepository facilityRoleRepository;

	private final FacilityTypeRepository facilityTypeRepository;

	private final FacilityRoleTypeRepository facilityRoleTypeRepository;

	private final PartyRepository partyRepository;

	private final PartyTypeRepository partyTypeRepository;

	private final ContactMechanismRepository contactMechanismRepository;

	public FacilityController(final FacilityRepository facilityRepository, final FacilityContactMechanismRepository facilityContactMechanismRepository, final FacilityRoleRepository facilityRoleRepository, final FacilityTypeRepository facilityTypeRepository, final FacilityRoleTypeRepository facilityRoleTypeRepository, final PartyRepository partyRepository, final PartyTypeRepository partyTypeRepository, final ContactMechanismRepository contactMechanismRepository) {
		this.facilityRepository = facilityRepository;
		this.facilityContactMechanismRepository = facilityContactMechanismRepository;
		this.facilityRoleRepository = facilityRoleRepository;
		this.facilityTypeRepository = facilityTypeRepository;
		this.facilityRoleTypeRepository = facilityRoleTypeRepository;
		this.partyRepository = partyRepository;
		this.partyTypeRepository = partyTypeRepository;
		this.contactMechanismRepository = contactMechanismRepository;
	}

	@QueryMapping
	public FacilityConnection facilities(@Argument PageInfo pageInfo) {
		final Page<Facility> facilities = facilityRepository.findByPartOfIdIsNull(pageInfoToPageable(pageInfo));
		final List<Edge<Facility>> facilityEdges = facilities.get()
				                                           .map(facility -> FacilityEdge.builder()
						                                                            .node(facility)
						                                                            .cursor(Cursor.builder().value(valueOf(facility.getId().hashCode())).build())
						                                                            .build())
				                                           .collect(Collectors.toList());
		return FacilityConnection.builder()
				       .edges(facilityEdges)
				       .pageInfo(pageInfo)
				       .build();
	}

	@SchemaMapping
	public FacilityType facilityType(Facility facility) {
		return facilityTypeRepository.findById(facility.getFacilityTypeId()).get();
	}

	@SchemaMapping
	public FacilityConnection madeUpOf(@Argument PageInfo pageInfo, Facility parent) {
		final Page<Facility> page = facilityRepository.findFacilitiesByPartOfId(parent.getPartOfId(), pageInfoToPageable(pageInfo));
		final List<Edge<Facility>> facilityEdges = page.stream()
				                                           .map(facility -> FacilityEdge.builder()
						                                                            .node(facility)
						                                                            .cursor(Cursor.builder().value(valueOf(facility.getId().hashCode())).build()).build())
				                                           .collect(Collectors.toList());
		return FacilityConnection.builder()
				       .edges(facilityEdges)
				       .pageInfo(pageInfo).build();
	}

	@SchemaMapping
	public FacilityContactMechanismConnection contactMechanisms(@Argument PageInfo pageInfo, Facility facility) {
		final Page<FacilityContactMechanism> page = facilityContactMechanismRepository.findByFacilityId(facility.getId(), pageInfoToPageable(pageInfo));
		final List<Edge<FacilityContactMechanism>> edges = page.stream()
				                                                   .map(facilityContactMechanism -> FacilityContactMechanismEdge.builder()
						                                                                                    .node(facilityContactMechanism)
						                                                                                    .cursor(Cursor.builder().value(valueOf(facilityContactMechanism.getId().hashCode())).build())
						                                                                                    .build())
				                                                   .collect(Collectors.toList());
		return FacilityContactMechanismConnection.builder()
				       .edges(edges)
				       .pageInfo(pageInfo)
				       .build();
	}

	@SchemaMapping
	public FacilityRoleConnection roles(@Argument PageInfo pageInfo, Facility facility) {
		final Page<FacilityRole> page = facilityRoleRepository.findByFacilityId(facility.getId(), pageInfoToPageable(pageInfo));
		final List<Edge<FacilityRole>> edges = page.stream()
				                                       .map(facilityRole -> FacilityRoleEdge.builder()
						                                                            .node(facilityRole)
						                                                            .cursor(Cursor.builder().value(valueOf(facilityRole.getId().hashCode())).build())
						                                                            .build())
				                                       .collect(Collectors.toList());
		return FacilityRoleConnection.builder()
				       .pageInfo(pageInfo)
				       .edges(edges)
				       .build();
	}

	@SchemaMapping
	public FacilityRoleType facilityRoleType(FacilityRole facilityRole) {
		return facilityRoleTypeRepository.findById(facilityRole.getFacilityRoleTypeId()).orElseThrow();
	}

	@SchemaMapping
	public Party party(FacilityRole facilityRole) {
		return partyRepository.findById(facilityRole.getPartyId()).orElseThrow();
	}

	@MutationMapping
	public Facility createFacility(@Argument NewFacility newFacility) {
		return facilityTypeRepository.findById(newFacility.getFacilityTypeId())
				       .map(facilityType -> {
					       Facility partOf = null;
					       if (newFacility.getPartOfId() != null) {
						       partOf = facilityRepository.findById(newFacility.getPartOfId()).get();
					       }
					       return facilityRepository.save(Facility.builder()
							                                      .description(newFacility.getDescription())
							                                      .squareFootage(newFacility.getSquareFootage())
							                                      .facilityTypeId(newFacility.getFacilityTypeId())
							                                      .build());
				       }).orElseThrow();
	}

	@MutationMapping
	public FacilityRole addFacilityRole(@Argument NewFacilityRole newFacilityRole) {
		return facilityRoleRepository.save(FacilityRole.builder()
				                                   .facilityId(newFacilityRole.getFacilityId())
				                                   .facilityRoleTypeId(newFacilityRole.getFacilityRoleTypeId())
				                                   .fromDate(newFacilityRole.getFromDate())
				                                   .partyId(newFacilityRole.getPartyId())
				                                   .thruDate(newFacilityRole.getThruDate())
				                                   .build());
	}

	@SchemaMapping
	public Facility facility(FacilityRole facilityRole) {
		return facilityRepository.findById(facilityRole.getFacilityId()).orElseThrow();
	}

	@SchemaMapping
	public PartyType partyType(Party party) {
		return partyTypeRepository.findById(party.getPartyTypeId()).orElseThrow();
	}

	@MutationMapping
	public FacilityContactMechanism addFacilityContactMechanism(@Argument NewFacilityContactMechanism newFacilityContactMechanism) {
		return facilityRepository.findById(newFacilityContactMechanism.getFacilityId())
				       .flatMap(facility -> contactMechanismRepository.findById(newFacilityContactMechanism.getContactMechanismId())
						                            .flatMap(contactMechanism -> Optional.of(facilityContactMechanismRepository.save(FacilityContactMechanism.builder()
								                                                                                                             .contactMechanismId(newFacilityContactMechanism.getContactMechanismId())
								                                                                                                             .facilityId(newFacilityContactMechanism.getFacilityId())
								                                                                                                             .fromDate(newFacilityContactMechanism.getFromDate())
								                                                                                                             .thruDate(newFacilityContactMechanism.getThruDate())
								                                                                                                             .build()))))
				       .orElseThrow();

	}

	@MutationMapping
	public FacilityRole expireFacilityRole(@Argument @NotNull UUID facilityRoleId, @Argument @NotNull LocalDate expirationDate) {
		return facilityRoleRepository.findById(facilityRoleId)
				       .flatMap(facilityRole -> {
					       facilityRole.setThruDate(expirationDate);
					       return Optional.of(facilityRoleRepository.save(facilityRole));
				       })
				       .orElseThrow();
	}

	@MutationMapping
	public FacilityContactMechanism expireFacilityContactMechanism(@Argument @NotNull UUID facilityContactMechanismId, @Argument @NotNull LocalDate expirationDate) {
		return facilityContactMechanismRepository.findById(facilityContactMechanismId)
				       .flatMap(facilityContactMechanism -> {
					       facilityContactMechanism.setThruDate(expirationDate);
					       return Optional.of(facilityContactMechanismRepository.save(facilityContactMechanism));
				       })
				       .orElseThrow();
	}
}
