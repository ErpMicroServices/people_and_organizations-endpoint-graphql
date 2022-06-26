package org.erpmicroservices.peopleandorganizations.endpoint.graphql.controllers;

import graphql.relay.Edge;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.Cursor;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.GeographicBoundaryConnection;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.GeographicBoundaryEdge;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.PageInfo;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.ContactMechanism;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.GeographicBoundary;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.ContactMechanismGeographicBoundaryRepository;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.ContactMechanismRepository;
import org.springframework.data.domain.Page;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.lang.String.valueOf;
import static org.erpmicroservices.peopleandorganizations.endpoint.graphql.misc.Utils.pageInfoToPageable;


@Controller
public class ContactMechanismController {

	private final ContactMechanismRepository repository;

	private final ContactMechanismGeographicBoundaryRepository contactMechanismGeographicBoundaryRepository;

	public ContactMechanismController(final ContactMechanismRepository repository, final ContactMechanismGeographicBoundaryRepository contactMechanismGeographicBoundaryRepository) {
		this.repository = repository;
		this.contactMechanismGeographicBoundaryRepository = contactMechanismGeographicBoundaryRepository;
	}

	@QueryMapping
	public List<ContactMechanism> contactMechanisms() {
		return (List<ContactMechanism>) repository.findAll();
	}

	@SchemaMapping
	public GeographicBoundaryConnection geographicBoundaries(UUID contactMechanismId, PageInfo pageInfo) {
		final Page<GeographicBoundary> geographicBoundaryPage = contactMechanismGeographicBoundaryRepository.findContactMechanismGeographicBoundariesByContactMechanism_Id(contactMechanismId, pageInfoToPageable(pageInfo));
		final List<Edge<GeographicBoundary>> geographicBoundaryEdges = geographicBoundaryPage.getContent().stream()
				                                                               .map(geographicBoundary -> GeographicBoundaryEdge.builder()
						                                                                                          .node(geographicBoundary)
						                                                                                          .cursor(Cursor.builder()
								                                                                                                  .value(valueOf(geographicBoundary.getId().hashCode()))
								                                                                                                  .build())
						                                                                                          .build())
				                                                               .collect(Collectors.toList());
		return GeographicBoundaryConnection.builder()
				       .edges(geographicBoundaryEdges)
				       .pageInfo(pageInfo)
				       .build();
	}
}
