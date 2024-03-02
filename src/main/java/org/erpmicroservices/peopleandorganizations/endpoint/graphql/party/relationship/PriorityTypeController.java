package org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.relationship;

import graphql.relay.Edge;
import org.erpmicroservices.peopleandorganizations.backend.entities.PriorityType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.Cursor;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.PageInfo;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.PriorityTypeRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.valueOf;
import static org.erpmicroservices.peopleandorganizations.endpoint.graphql.misc.Utils.pageInfoToPageable;


@Controller
public class PriorityTypeController {

	private final PriorityTypeRepository repository;

	public PriorityTypeController(final PriorityTypeRepository repository) {
		this.repository = repository;
	}

	@QueryMapping
	public PriorityTypeConnection partyRelationshipPriorityTypes(@Argument PageInfo pageInfo) {
		final List<Edge<PriorityType>> edges = repository.findPartyRelationshipPriorityTypesByParentIdIsNull(pageInfoToPageable(pageInfo)).stream()
				                                       .map(node -> PriorityTypeEdge.builder()
						                                                    .node(node)
						                                                    .cursor(Cursor.builder().value(valueOf(node.getId().hashCode())).build())
						                                                    .build())
				                                       .collect(Collectors.toList());
		return PriorityTypeConnection.builder()
				       .edges(edges)
				       .pageInfo(pageInfo)
				       .build();
	}

}
