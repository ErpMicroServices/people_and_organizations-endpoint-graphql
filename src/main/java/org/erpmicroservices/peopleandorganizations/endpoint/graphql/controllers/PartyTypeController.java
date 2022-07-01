package org.erpmicroservices.peopleandorganizations.endpoint.graphql.controllers;

import graphql.relay.Edge;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.Cursor;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.PageInfo;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.PartyTypeConnection;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.PartyTypeEdge;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.PartyType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.PartyTypeRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.valueOf;
import static org.erpmicroservices.peopleandorganizations.endpoint.graphql.misc.Utils.pageInfoToPageable;

@Controller
public class PartyTypeController {

	private final PartyTypeRepository partyTypeRepository;

	public PartyTypeController(final PartyTypeRepository partyTypeRepository) {
		this.partyTypeRepository = partyTypeRepository;
	}

	@QueryMapping
	public PartyTypeConnection partyTypes(@Argument PageInfo pageInfo) {
		final List<Edge<PartyType>> edges = partyTypeRepository.findPartyTypesByParentIsNull(pageInfoToPageable(pageInfo)).stream()
				                                    .map(partyType -> PartyTypeEdge.builder()
						                                                      .node(partyType)
						                                                      .cursor(Cursor.builder().value(valueOf(partyType.getId().hashCode())).build())
						                                                      .build())
				                                    .collect(Collectors.toList());
		return PartyTypeConnection.builder()
				       .edges(edges)
				       .pageInfo(pageInfo)
				       .build();
	}

	@SchemaMapping
	public PartyTypeConnection children(PartyType parent, @Argument PageInfo pageInfo) {
		final List<Edge<PartyType>> edges = partyTypeRepository.findPartyTypesByParentEquals(parent, pageInfoToPageable(pageInfo)).stream()
				                                    .map(partyType -> PartyTypeEdge.builder()
						                                                      .node(partyType)
						                                                      .cursor(Cursor.builder().value(valueOf(partyType.getId().hashCode())).build())
						                                                      .build())
				                                    .collect(Collectors.toList());
		return PartyTypeConnection.builder()
				       .edges(edges)
				       .pageInfo(pageInfo)
				       .build();
	}
}
