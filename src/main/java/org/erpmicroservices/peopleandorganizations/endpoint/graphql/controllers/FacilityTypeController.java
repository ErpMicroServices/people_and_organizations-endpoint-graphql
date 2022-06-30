package org.erpmicroservices.peopleandorganizations.endpoint.graphql.controllers;

import graphql.relay.Edge;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.Cursor;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.FacilityTypeConnection;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.FacilityTypeEdge;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.PageInfo;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.FacilityType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.FacilityTypeRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.valueOf;
import static org.erpmicroservices.peopleandorganizations.endpoint.graphql.misc.Utils.pageInfoToPageable;


@Controller
public class FacilityTypeController {

	private final FacilityTypeRepository repository;

	public FacilityTypeController(final FacilityTypeRepository repository) {
		this.repository = repository;
	}

	@QueryMapping
	public FacilityTypeConnection facilityTypes(@Argument PageInfo pageInfo) {
		final List<Edge<FacilityType>> edges = repository.findFacilityTypesByParentIsNull(pageInfoToPageable(pageInfo)).stream()
				                                       .map(facilityType -> FacilityTypeEdge.builder()
						                                                            .node(facilityType)
						                                                            .cursor(Cursor.builder().value(valueOf(facilityType.getId().hashCode())).build())
						                                                            .build())
				                                       .collect(Collectors.toList());
		return FacilityTypeConnection.builder()
				       .edges(edges)
				       .pageInfo(pageInfo)
				       .build();
	}

	@SchemaMapping
	public FacilityTypeConnection children(FacilityType parent, @Argument PageInfo pageInfo) {
		final List<Edge<FacilityType>> edges = repository.findFacilityTypesByParentEquals(parent, pageInfoToPageable(pageInfo)).stream()
				                                       .map(facilityType -> FacilityTypeEdge.builder()
						                                                            .node(facilityType)
						                                                            .cursor(Cursor.builder().value(valueOf(facilityType.getId().hashCode())).build())
						                                                            .build())
				                                       .collect(Collectors.toList());
		return FacilityTypeConnection.builder()
				       .edges(edges)
				       .pageInfo(pageInfo)
				       .build();
	}
}
