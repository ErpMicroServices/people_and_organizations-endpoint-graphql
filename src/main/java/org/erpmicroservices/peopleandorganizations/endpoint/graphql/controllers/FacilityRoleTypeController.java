package org.erpmicroservices.peopleandorganizations.endpoint.graphql.controllers;

import graphql.relay.Edge;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.Cursor;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.FacilityRoleTypeConnection;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.FacilityRoleTypeEdge;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.PageInfo;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.FacilityRoleType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.FacilityRoleTypeRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.valueOf;
import static org.erpmicroservices.peopleandorganizations.endpoint.graphql.misc.Utils.pageInfoToPageable;


@Controller
public class FacilityRoleTypeController {

	private final FacilityRoleTypeRepository repository;

	public FacilityRoleTypeController(final FacilityRoleTypeRepository repository) {
		this.repository = repository;
	}

	@QueryMapping
	public FacilityRoleTypeConnection facilityRoleTypes(@Argument PageInfo pageInfo) {
		final List<Edge<FacilityRoleType>> edges = repository.findFacilityRoleTypesByParentIsNull(pageInfoToPageable(pageInfo)).stream()
				                                           .map(facilityRoleType -> FacilityRoleTypeEdge.builder()
						                                                                    .node(facilityRoleType)
						                                                                    .cursor(Cursor.builder().value(valueOf(facilityRoleType.getId().hashCode())).build())
						                                                                    .build())
				                                           .collect(Collectors.toList());
		return FacilityRoleTypeConnection.builder()
				       .edges(edges)
				       .pageInfo(pageInfo)
				       .build();
	}

	@SchemaMapping
	public FacilityRoleTypeConnection children(FacilityRoleType parent, @Argument PageInfo pageInfo) {
		final List<Edge<FacilityRoleType>> edges = repository.findFacilityRoleTypesByParentEquals(parent, pageInfoToPageable(pageInfo)).stream()
				                                           .map(facilityRoleType -> FacilityRoleTypeEdge.builder()
						                                                                    .node(facilityRoleType)
						                                                                    .cursor(Cursor.builder().value(valueOf(facilityRoleType.getId().hashCode())).build())
						                                                                    .build())
				                                           .collect(Collectors.toList());
		return FacilityRoleTypeConnection.builder()
				       .edges(edges)
				       .pageInfo(pageInfo)
				       .build();
	}
}
