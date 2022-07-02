package org.erpmicroservices.peopleandorganizations.endpoint.graphql.facility;

import graphql.relay.Edge;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.Cursor;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.PageInfo;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.*;
import org.springframework.data.domain.Page;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import javax.validation.constraints.NotNull;
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

	private final ContactMechanismRepository contactMechanismRepository;

	public FacilityController(final FacilityRepository repository, final FacilityContactMechanismRepository facilityContactMechanismRepository, final FacilityRoleRepository facilityRoleRepository, final FacilityTypeRepository facilityTypeRepository, final FacilityRoleTypeRepository facilityRoleTypeRepository, final PartyRepository partyRepository, final ContactMechanismRepository contactMechanismRepository) {
		this.facilityRepository = repository;
		this.facilityContactMechanismRepository = facilityContactMechanismRepository;
		this.facilityRoleRepository = facilityRoleRepository;
		this.facilityTypeRepository = facilityTypeRepository;
		this.facilityRoleTypeRepository = facilityRoleTypeRepository;
		this.partyRepository = partyRepository;
		this.contactMechanismRepository = contactMechanismRepository;
	}

	@QueryMapping
	public FacilityConnection facilities(@Argument PageInfo pageInfo) {
		final Page<Facility> facilities = facilityRepository.findByPartOfIsNull(pageInfoToPageable(pageInfo));
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
	public FacilityConnection madeUpOf(@Argument PageInfo pageInfo, Facility parent) {
		final Page<Facility> page = facilityRepository.findFacilitiesByPartOf(parent, pageInfoToPageable(pageInfo));
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
	public FacilityContactMechanismConnection facilityContactMechanisms(@Argument PageInfo pageInfo, Facility facility) {
		final Page<FacilityContactMechanism> page = facilityContactMechanismRepository.findByFacility_Id(facility.getId(), pageInfoToPageable(pageInfo));
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
	public FacilityConnection roles(@Argument PageInfo pageInfo, Facility parent) {
		final Page<Facility> page = facilityRoleRepository.findByFacility(parent, pageInfoToPageable(pageInfo));
		final List<Edge<Facility>> edges = page.stream()
				                                   .map(facility -> FacilityEdge.builder()
						                                                    .node(facility)
						                                                    .cursor(Cursor.builder().value(valueOf(facility.getId().hashCode())).build())
						                                                    .build())
				                                   .collect(Collectors.toList());
		return FacilityConnection.builder()
				       .pageInfo(pageInfo)
				       .edges(edges)
				       .build();
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
							                                      .facilityType(facilityType)
							                                      .partOf(partOf)
							                                      .build());
				       }).orElseThrow();
	}

	@MutationMapping
	public FacilityRole addFacilityRole(@Argument NewFacilityRole newFacilityRole) {
		return facilityRepository.findById(newFacilityRole.getFacilityId())
				       .flatMap(facility -> facilityRoleTypeRepository.findById(newFacilityRole.getFacilityRoleTypeId())
						                            .flatMap(facilityRoleType -> partyRepository.findById(newFacilityRole.getPartyId())
								                                                         .flatMap(party -> Optional.of(facilityRoleRepository.save(FacilityRole.builder()
										                                                                                                                   .facility(facility)
										                                                                                                                   .facilityRoleType(facilityRoleType)
										                                                                                                                   .party(party)
										                                                                                                                   .fromDate(newFacilityRole.getFromDate())
										                                                                                                                   .thruDate(newFacilityRole.getThruDate())
										                                                                                                                   .build())))))
				       .orElseThrow();
	}

	@MutationMapping
	public FacilityContactMechanism addFacilityContactMechanism(@Argument NewFacilityContactMechanism newFacilityContactMechanism) {
		return facilityRepository.findById(newFacilityContactMechanism.getFacilityId())
				       .flatMap(facility -> contactMechanismRepository.findById(newFacilityContactMechanism.getContactMechanismId())
						                            .flatMap(contactMechanism -> Optional.of(facilityContactMechanismRepository.save(FacilityContactMechanism.builder()
								                                                                                                             .contactMechanism(contactMechanism)
								                                                                                                             .facility(facility)
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
