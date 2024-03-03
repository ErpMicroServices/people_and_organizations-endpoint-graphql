package org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.relationship;

import graphql.relay.Edge;
import org.erpmicroservices.peopleandorganizations.backend.entities.PartyRelationshipTypeEntity;
import org.erpmicroservices.peopleandorganizations.backend.repositories.PartyRelationshipTypeRepository;
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
public class PartyRelationshipTypeController {

	private final PartyRelationshipTypeRepository repository;

	public PartyRelationshipTypeController(final PartyRelationshipTypeRepository repository) {
		this.repository = repository;
	}

	@QueryMapping
	public PartyRelationshipTypeConnection partyRelationshipTypes(@Argument PageInfo pageInfo) {
		final List<Edge<PartyRelationshipTypeEntity>> edges = repository.findPartyRelationshipTypesByParentIdIsNull(pageInfoToPageable(pageInfo)).stream()
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
	public PartyRelationshipTypeConnection children(PartyRelationshipTypeEntity parent, @Argument PageInfo pageInfo) {
		final List<Edge<PartyRelationshipTypeEntity>> edges = repository.findPartyRelationshipTypesByParentId(parent.getId(), pageInfoToPageable(pageInfo)).stream()
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
