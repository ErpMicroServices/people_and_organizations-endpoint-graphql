package org.erpmicroservices.peopleandorganizations.endpoint.graphql.contactmechanism.controllers;

import graphql.relay.Edge;
import org.erpmicroservices.peopleandorganizations.backend.entities.ContactMechanismEntity;
import org.erpmicroservices.peopleandorganizations.backend.entities.ContactMechanismTypeEntity;
import org.erpmicroservices.peopleandorganizations.backend.entities.GeographicBoundaryEntity;
import org.erpmicroservices.peopleandorganizations.backend.repositories.ContactMechanismGeographicBoundaryRepository;
import org.erpmicroservices.peopleandorganizations.backend.repositories.ContactMechanismRepository;
import org.erpmicroservices.peopleandorganizations.backend.repositories.ContactMechanismTypeRepository;
import org.erpmicroservices.peopleandorganizations.backend.repositories.GeographicBoundaryRepository;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.contactmechanism.models.ContactMechanism;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.contactmechanism.models.ContactMechanismConnection;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.contactmechanism.models.ContactMechanismEdge;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.Cursor;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.PageInfo;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.geographicboundary.GeographicBoundaryConnection;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.geographicboundary.GeographicBoundaryEdge;
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
																							  .build())
						                                                              .cursor(Cursor.builder().value(valueOf(contactMechanism.getId().hashCode())).build())
						                                                              .build())
				                                     .collect(Collectors.toList());
		return ContactMechanismConnection.builder()
				       .edges(edges)
				       .pageInfo(pageInfo)
				       .build();
	}

	@SchemaMapping(typeName = "FacilityContactMechanism", field = "contactMechanism")
	public ContactMechanism facilityContactMechanism(FacilityContactMechanism facilityContactMechanism) {
		return contactMechanismRepository.findById(facilityContactMechanism.getContactMechanismId()).orElseThrow();
	}

	@SchemaMapping(typeName = "ContactMechanism", field = "geographicBoundaries")
	public GeographicBoundaryConnection geographicBoundaries(@Argument PageInfo pageInfo, ContactMechanism ContactMechanism) {
		final List<Edge<GeographicBoundaryEntity>> edges = contactMechanismGeographicBoundaryRepository
				                                             .findGeographicBoundaryByContactMechanismId(ContactMechanism.getId(), pageInfoToPageable(pageInfo))
				                                             .stream()
				                                             .map(geographicBoundary -> GeographicBoundaryEdge.builder()
						                                                                        .node(geographicBoundary)
						                                                                        .cursor(Cursor.builder().value(valueOf(geographicBoundary.getId().hashCode())).build())
						                                                                        .build())
				                                             .collect(Collectors.toList());

		return GeographicBoundaryConnection.builder()
				       .edges(edges)
				       .pageInfo(pageInfo)
				       .build();
	}

	@SchemaMapping
	public ContactMechanismTypeEntity contactMechanismType(ContactMechanism ContactMechanism) {
		return contactMechanismTypeRepository.findById(ContactMechanism.getContactMechanismTypeId()).get();
	}
}
