package org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.relationship;

import graphql.relay.Edge;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.Cursor;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.PageInfo;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.PartyRelationshipStatusTypeRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.valueOf;
import static org.erpmicroservices.peopleandorganizations.endpoint.graphql.misc.Utils.pageInfoToPageable;


@Controller
public class PartyRelationshipStatusTypeController {

	private final PartyRelationshipStatusTypeRepository repository;

	public PartyRelationshipStatusTypeController(final PartyRelationshipStatusTypeRepository repository) {
		this.repository = repository;
	}

	@QueryMapping
	public PartyRelationshipStatusTypeConnection partyRelationshipStatusTypes(@Argument PageInfo pageInfo) {
		final List<Edge<PartyRelationshipStatusType>> edges = repository.findPartyRelationshipStatusTypesByParentIsNull(pageInfoToPageable(pageInfo)).stream()
				                                                      .map(node -> PartyRelationshipStatusTypeEdge.builder()
						                                                                   .node(node)
						                                                                   .cursor(Cursor.builder().value(valueOf(node.getId().hashCode())).build())
						                                                                   .build())
				                                                      .collect(Collectors.toList());
		return PartyRelationshipStatusTypeConnection.builder()
				       .edges(edges)
				       .pageInfo(pageInfo)
				       .build();
	}

	@SchemaMapping
	public PartyRelationshipStatusTypeConnection children(PartyRelationshipStatusType parent, @Argument PageInfo pageInfo) {
		final List<Edge<PartyRelationshipStatusType>> edges = repository.findPartyRelationshipStatusTypesByParentEquals(parent, pageInfoToPageable(pageInfo)).stream()
				                                                      .map(node -> PartyRelationshipStatusTypeEdge.builder()
						                                                                   .node(node)
						                                                                   .cursor(Cursor.builder().value(valueOf(node.getId().hashCode())).build())
						                                                                   .build())
				                                                      .collect(Collectors.toList());
		return PartyRelationshipStatusTypeConnection.builder()
				       .edges(edges)
				       .pageInfo(pageInfo)
				       .build();
	}
}
