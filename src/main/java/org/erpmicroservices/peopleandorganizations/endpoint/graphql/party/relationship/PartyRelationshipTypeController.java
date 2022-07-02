package org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.relationship;

import graphql.relay.Edge;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.Cursor;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.PageInfo;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.PartyRelationshipTypeRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.valueOf;
import static org.erpmicroservices.peopleandorganizations.endpoint.graphql.misc.Utils.pageInfoToPageable;


@Controller
public class PartyRelationshipTypeController {

	private final PartyRelationshipTypeRepository repository;

	public PartyRelationshipTypeController(final PartyRelationshipTypeRepository repository) {
		this.repository = repository;
	}

	@QueryMapping
	public PartyRelationshipTypeConnection partyRelationshipTypes(@Argument PageInfo pageInfo) {
		final List<Edge<PartyRelationshipType>> edges = repository.findPartyRelationshipTypesByParentIsNull(pageInfoToPageable(pageInfo)).stream()
				                                                .map(node -> PartyRelationshipTypeEdge.builder()
						                                                             .node(node)
						                                                             .cursor(Cursor.builder().value(valueOf(node.getId().hashCode())).build())
						                                                             .build())
				                                                .collect(Collectors.toList());
		return PartyRelationshipTypeConnection.builder()
				       .edges(edges)
				       .pageInfo(pageInfo)
				       .build();
	}

	@SchemaMapping
	public PartyRelationshipTypeConnection children(PartyRelationshipType parent, @Argument PageInfo pageInfo) {
		final List<Edge<PartyRelationshipType>> edges = repository.findPartyRelationshipTypesByParentEquals(parent, pageInfoToPageable(pageInfo)).stream()
				                                                .map(node -> PartyRelationshipTypeEdge.builder()
						                                                             .node(node)
						                                                             .cursor(Cursor.builder().value(valueOf(node.getId().hashCode())).build())
						                                                             .build())
				                                                .collect(Collectors.toList());
		return PartyRelationshipTypeConnection.builder()
				       .edges(edges)
				       .pageInfo(pageInfo)
				       .build();
	}
}
