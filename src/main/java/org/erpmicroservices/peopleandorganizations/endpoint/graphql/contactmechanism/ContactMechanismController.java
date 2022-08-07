package org.erpmicroservices.peopleandorganizations.endpoint.graphql.contactmechanism;

import graphql.relay.Edge;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.Cursor;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.PageInfo;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.facility.Facility;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.ContactMechanismGeographicBoundaryRepository;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.ContactMechanismRepository;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.ContactMechanismTypeRepository;
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

	private final ContactMechanismRepository repository;

	private final ContactMechanismGeographicBoundaryRepository contactMechanismGeographicBoundaryRepository;

	private final ContactMechanismTypeRepository contactMechanismTypeRepository;

	public ContactMechanismController(final ContactMechanismRepository repository, final ContactMechanismTypeRepository contactMechanismTypeRepository, final ContactMechanismGeographicBoundaryRepository contactMechanismGeographicBoundaryRepository) {
		this.repository = repository;
		this.contactMechanismGeographicBoundaryRepository = contactMechanismGeographicBoundaryRepository;
		this.contactMechanismTypeRepository = contactMechanismTypeRepository;
	}

	@QueryMapping
	public ContactMechanismConnection contactMechanisms(PageInfo pageInfo) {
		final Page<ContactMechanism> page = repository.findAll(pageInfoToPageable(pageInfo));
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

	@SchemaMapping
	public ContactMechanism facilityContactMechanism(Facility facility) {
		return repository.findById(facility.getId()).orElseThrow();
	}

	@SchemaMapping(typeName = "ContactMechanism", field = "geographicBoundaries")
	public ContactMechanismGeographicBoundaryConnection geographicBoundaries(@Argument PageInfo pageInfo, ContactMechanism contactMechanism) {
		final Page<ContactMechanismGeographicBoundary> page = contactMechanismGeographicBoundaryRepository.findContactMechanismGeographicBoundariesByContactMechanismId(contactMechanism.getId(), pageInfoToPageable(pageInfo));
		final List<Edge<ContactMechanismGeographicBoundary>> edges = page.getContent().stream()
				                                                             .map(geographicBoundary -> ContactMechanismGeographicBoundaryEdge.builder()
						                                                                                        .node(geographicBoundary)
						                                                                                        .cursor(Cursor.builder()
								                                                                                                .value(valueOf(geographicBoundary.getId().hashCode()))
								                                                                                                .build())
						                                                                                        .build())
				                                                             .collect(Collectors.toList());
		return ContactMechanismGeographicBoundaryConnection.builder()
				       .edges(edges)
				       .pageInfo(pageInfo)
				       .build();
	}

	@SchemaMapping
	public ContactMechanismType contactMechanismType(ContactMechanism contactMechanism) {
		return contactMechanismTypeRepository.findById(contactMechanism.getContactMechanismTypeId()).get();
	}
}
