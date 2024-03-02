package org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.role;

import graphql.relay.Edge;
import org.erpmicroservices.peopleandorganizations.backend.entities.PartyRoleType;
import org.erpmicroservices.peopleandorganizations.backend.repositories.PartyRoleTypeRepository;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.Cursor;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.PageInfo;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.valueOf;
import static org.erpmicroservices.peopleandorganizations.endpoint.graphql.misc.Utils.pageInfoToPageable;


@Controller
public class PartyRoleTypeController {

	private final PartyRoleTypeRepository repository;

	public PartyRoleTypeController(final PartyRoleTypeRepository repository) {
		this.repository = repository;
	}

	@QueryMapping
	public PartyRoleTypeConnection partyRoleTypes(@Argument PageInfo pageInfo) {
		final List<Edge<PartyRoleType>> edges = repository.findPartyRoleTypesByParentIdIsNull(pageInfoToPageable(pageInfo)).stream()
				                                        .map(node -> PartyRoleTypeEdge.builder()
						                                                     .node(node)
						                                                     .cursor(Cursor.builder().value(valueOf(node.getId().hashCode())).build())
						                                                     .build())
				                                        .collect(Collectors.toList());
		return PartyRoleTypeConnection.builder()
				       .edges(edges)
				       .pageInfo(pageInfo)
				       .build();
	}

	@SchemaMapping
	public PartyRoleTypeConnection children(PartyRoleType parent, @Argument PageInfo pageInfo) {
		final List<Edge<PartyRoleType>> edges = repository.findPartyRoleTypesByParentId(parent.getId(), pageInfoToPageable(pageInfo)).stream()
				                                        .map(node -> PartyRoleTypeEdge.builder()
						                                                     .node(node)
						                                                     .cursor(Cursor.builder().value(valueOf(node.getId().hashCode())).build())
						                                                     .build())
				                                        .collect(Collectors.toList());
		return PartyRoleTypeConnection.builder()
				       .edges(edges)
				       .pageInfo(pageInfo)
				       .build();
	}
}
