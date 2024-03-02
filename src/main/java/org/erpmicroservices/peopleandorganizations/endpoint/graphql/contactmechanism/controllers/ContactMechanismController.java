package org.erpmicroservices.peopleandorganizations.endpoint.graphql.contactmechanism.controllers;

import graphql.relay.Edge;
import org.erpmicroservices.peopleandorganizations.backend.entities.ContactMechanism;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.contactmechanism.models.ContactMechanismConnection;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.contactmechanism.models.ContactMechanismEdge;
import org.erpmicroservices.peopleandorganizations.backend.entities.ContactMechanismType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.Cursor;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.PageInfo;
import org.erpmicroservices.peopleandorganizations.backend.entities.FacilityContactMechanism;
import org.erpmicroservices.peopleandorganizations.backend.entities.GeographicBoundary;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.geographicboundary.GeographicBoundaryConnection;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.geographicboundary.GeographicBoundaryEdge;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.ContactMechanismGeographicBoundaryRepository;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.ContactMechanismRepository;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.ContactMechanismTypeRepository;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.GeographicBoundaryRepository;
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
		final Page<ContactMechanism> page = contactMechanismRepository.findAll(pageInfoToPageable(pageInfo));
		List<Edge<ContactMechanism>> edges = page.stream()
				                                     .map(contactMechanism -> ContactMechanismEdge.builder()
						                                                              .node(contactMechanism)
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
	public GeographicBoundaryConnection geographicBoundaries(@Argument PageInfo pageInfo, ContactMechanism contactMechanism) {
		final List<Edge<GeographicBoundary>> edges = contactMechanismGeographicBoundaryRepository
				                                             .findGeographicBoundaryByContactMechanismId(contactMechanism.getId(), pageInfoToPageable(pageInfo))
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
	public ContactMechanismType contactMechanismType(ContactMechanism contactMechanism) {
		return contactMechanismTypeRepository.findById(contactMechanism.getContactMechanismTypeId()).get();
	}
}
