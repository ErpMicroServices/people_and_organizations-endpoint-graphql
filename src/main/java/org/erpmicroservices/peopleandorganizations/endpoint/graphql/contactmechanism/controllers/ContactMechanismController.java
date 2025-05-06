package org.erpmicroservices.peopleandorganizations.endpoint.graphql.contactmechanism.controllers;

import graphql.relay.Edge;
import org.erpmicroservices.peopleandorganizations.backend.entities.ContactMechanismEntity;
import org.erpmicroservices.peopleandorganizations.backend.entities.GeographicBoundaryEntity;
import org.erpmicroservices.peopleandorganizations.backend.repositories.ContactMechanismGeographicBoundaryRepository;
import org.erpmicroservices.peopleandorganizations.backend.repositories.ContactMechanismRepository;
import org.erpmicroservices.peopleandorganizations.backend.repositories.ContactMechanismTypeRepository;
import org.erpmicroservices.peopleandorganizations.backend.repositories.GeographicBoundaryRepository;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.contactmechanism.models.ContactMechanism;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.contactmechanism.models.ContactMechanismConnection;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.contactmechanism.models.ContactMechanismEdge;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.contactmechanism.models.ContactMechanismType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.Cursor;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.PageInfo;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.geographicboundary.GeographicBoundary;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.geographicboundary.GeographicBoundaryConnection;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.geographicboundary.GeographicBoundaryEdge;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.geographicboundary.GeographicBoundaryType;
import org.springframework.data.domain.Page;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.valueOf;
import static org.erpmicroservices.peopleandorganizations.endpoint.graphql.misc.Utils.pageInfoToPageable;


@Controller
public class ContactMechanismController {

	private final ContactMechanismRepository contactMechanismRepository;

	private final ContactMechanismGeographicBoundaryRepository contactMechanismGeographicBoundaryRepository;

	private final ContactMechanismTypeRepository contactMechanismTypeRepository;

	private final GeographicBoundaryRepository geographicBoundaryRepository;

	public ContactMechanismController(final ContactMechanismRepository contactMechanismRepository, final ContactMechanismTypeRepository contactMechanismTypeRepository, final ContactMechanismGeographicBoundaryRepository contactMechanismGeographicBoundaryRepository, final GeographicBoundaryRepository geographicBoundaryRepository) {
		this.contactMechanismRepository = contactMechanismRepository;
		this.contactMechanismGeographicBoundaryRepository = contactMechanismGeographicBoundaryRepository;
		this.contactMechanismTypeRepository = contactMechanismTypeRepository;
		this.geographicBoundaryRepository = geographicBoundaryRepository;
	}

	@QueryMapping
	public ContactMechanismConnection contactMechanisms(PageInfo pageInfo) {
		final Page<ContactMechanismEntity> page = contactMechanismRepository.findAll(pageInfoToPageable(pageInfo));
		List<Edge<ContactMechanism>> edges = page.stream()
				                                     .map(contactMechanism -> ContactMechanismEdge.builder()
						                                                              .node(ContactMechanism.builder()
																							  .id(contactMechanism.getId())
																							  .endPoint(contactMechanism.getEndPoint())
																							  .directions(contactMechanism.getDirections())
																							  .contactMechanismType(contactMechanismTypeRepository.findById(contactMechanism.getContactMechanismTypeId())
																									  .map(type -> ContactMechanismType.builder()
																											  .id(type.getId())
																											  .description(type.getDescription())
																											  .build())
																									  .orElse(null))
																							  .build())
						                                                              .cursor(Cursor.builder().value(valueOf(contactMechanism.getId().hashCode())).build())
						                                                              .build())
				                                     .collect(Collectors.toList());
		return ContactMechanismConnection.builder()
				       .edges(edges)
				       .pageInfo(pageInfo)
				       .build();
	}

	@SchemaMapping(typeName = "FacilityContactMechanism", field = "contactMechanismEntity")
	public ContactMechanism facilityContactMechanism(
			org.erpmicroservices.peopleandorganizations.endpoint.graphql.facility.models.FacilityContactMechanism facilityContactMechanism) {
		return contactMechanismRepository.findById(facilityContactMechanism.getContactMechanism().getId())
				.map(entity -> ContactMechanism.builder()
						.id(entity.getId())
						.endPoint(entity.getEndPoint())
						.directions(entity.getDirections())
						.contactMechanismType(contactMechanismTypeRepository.findById(entity.getContactMechanismTypeId())
								.map(type -> ContactMechanismType.builder()
										.id(type.getId())
										.description(type.getDescription())
										.build())
								.orElse(null))
						.build())
				.orElse(null);
	}

	@SchemaMapping(typeName = "ContactMechanism", field = "geographicBoundaries")
	public GeographicBoundaryConnection geographicBoundaries(@Argument PageInfo pageInfo, ContactMechanism contactMechanism) {
		final List<Edge<GeographicBoundary>> edges = contactMechanismGeographicBoundaryRepository
				                                             .findGeographicBoundaryByContactMechanismId(contactMechanism.getId(), pageInfoToPageable(pageInfo))
				                                             .stream()
				                                             .map(geographicBoundaryEntity -> {
					                                             GeographicBoundary geographicBoundary = GeographicBoundary.builder()
							                                             .id(geographicBoundaryEntity.getId())
							                                             .name(geographicBoundaryEntity.getName())
							                                             .geoCode(geographicBoundaryEntity.getGeoCode())
							                                             .abbreviation(geographicBoundaryEntity.getAbbreviation())
							                                             .build();

					                                             return GeographicBoundaryEdge.builder()
							                                             .node(geographicBoundary)
							                                             .cursor(Cursor.builder().value(valueOf(geographicBoundaryEntity.getId().hashCode())).build())
							                                             .build();
				                                             })
				                                             .collect(Collectors.toList());

		return GeographicBoundaryConnection.builder()
				       .edges(edges)
				       .pageInfo(pageInfo)
				       .build();
	}

	@SchemaMapping
	public ContactMechanismType contactMechanismType(ContactMechanism contactMechanism) {
		return contactMechanismTypeRepository.findById(contactMechanism.getContactMechanismType().getId())
				.map(entity -> ContactMechanismType.builder()
						.id(entity.getId())
						.description(entity.getDescription())
						.build())
				.orElse(null);
	}
}
