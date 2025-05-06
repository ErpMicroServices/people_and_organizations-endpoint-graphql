package org.erpmicroservices.peopleandorganizations.endpoint.graphql.facility.controllers;

import graphql.relay.Edge;
import org.erpmicroservices.peopleandorganizations.backend.entities.FacilityRoleTypeEntity;
import org.erpmicroservices.peopleandorganizations.backend.repositories.FacilityRoleTypeRepository;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.Cursor;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.PageInfo;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.facility.models.FacilityRoleTypeEdge;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.facility.models.FacilityRoleTypeConnection;
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
		final List<Edge<FacilityRoleTypeEntity>> edges = repository.findFacilityRoleTypesByParentIdIsNull(pageInfoToPageable(pageInfo)).stream()
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
	public FacilityRoleTypeConnection children(FacilityRoleTypeEntity parent, @Argument PageInfo pageInfo) {
		final List<Edge<FacilityRoleTypeEntity>> edges = repository.findFacilityRoleTypesByParentId(parent.getId(), pageInfoToPageable(pageInfo)).stream()
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
