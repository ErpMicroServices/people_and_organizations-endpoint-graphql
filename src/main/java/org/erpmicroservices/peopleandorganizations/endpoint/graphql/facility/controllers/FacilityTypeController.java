package org.erpmicroservices.peopleandorganizations.endpoint.graphql.facility.controllers;

import graphql.relay.Edge;
import org.erpmicroservices.peopleandorganizations.backend.entities.FacilityTypeEntity;
import org.erpmicroservices.peopleandorganizations.backend.repositories.FacilityTypeRepository;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.Cursor;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.PageInfo;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.facility.models.FacilityTypeEdge;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.facility.models.FacilityTypeConnection;
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
		final List<Edge<FacilityTypeEntity>> edges = repository.findFacilityTypesByParentIdIsNull(pageInfoToPageable(pageInfo)).stream()
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
	public FacilityTypeConnection children(FacilityTypeEntity parent, @Argument PageInfo pageInfo) {
		final List<Edge<FacilityTypeEntity>> edges = repository.findFacilityTypesByParentId(parent.getId(), pageInfoToPageable(pageInfo)).stream()
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
