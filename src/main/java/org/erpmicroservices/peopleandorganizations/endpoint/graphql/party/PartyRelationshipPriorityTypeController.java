package org.erpmicroservices.peopleandorganizations.endpoint.graphql.party;

import graphql.relay.Edge;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.Cursor;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.PageInfo;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.PartyRelationshipPriorityTypeRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.valueOf;
import static org.erpmicroservices.peopleandorganizations.endpoint.graphql.misc.Utils.pageInfoToPageable;


@Controller
public class PartyRelationshipPriorityTypeController {

	private final PartyRelationshipPriorityTypeRepository repository;

	public PartyRelationshipPriorityTypeController(final PartyRelationshipPriorityTypeRepository repository) {
		this.repository = repository;
	}

	@QueryMapping
	public PartyRelationshipPriorityTypeConnection partyRelationshipPriorityTypes(@Argument PageInfo pageInfo) {
		final List<Edge<PriorityType>> edges = repository.findPartyRelationshipPriorityTypesByParentIsNull(pageInfoToPageable(pageInfo)).stream()
				                                       .map(node -> PartyRelationshipPriorityTypeEdge.builder()
						                                                    .node(node)
						                                                    .cursor(Cursor.builder().value(valueOf(node.getId().hashCode())).build())
						                                                    .build())
				                                       .collect(Collectors.toList());
		return PartyRelationshipPriorityTypeConnection.builder()
				       .edges(edges)
				       .pageInfo(pageInfo)
				       .build();
	}

}
