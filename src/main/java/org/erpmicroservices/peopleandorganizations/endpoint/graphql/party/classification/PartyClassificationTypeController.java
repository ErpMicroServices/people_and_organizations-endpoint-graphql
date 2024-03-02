package org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.classification;

import graphql.relay.Edge;
import org.erpmicroservices.peopleandorganizations.backend.entities.PartyClassificationType;
import org.erpmicroservices.peopleandorganizations.backend.repositories.PartyClassificationTypeRepository;
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
public class PartyClassificationTypeController {

	private final PartyClassificationTypeRepository repository;

	public PartyClassificationTypeController(final PartyClassificationTypeRepository repository) {
		this.repository = repository;
	}

	@QueryMapping
	public PartyClassificationTypeConnection partyClassificationTypes(@Argument PageInfo pageInfo) {
		final List<Edge<PartyClassificationType>> edges = repository.findPartyClassificationTypesByParentIdIsNull(pageInfoToPageable(pageInfo)).stream()
				                                                  .map(node -> PartyClassificationTypeEdge.builder()
						                                                               .node(node)
						                                                               .cursor(Cursor.builder().value(valueOf(node.getId().hashCode())).build())
						                                                               .build())
				                                                  .collect(Collectors.toList());
		return PartyClassificationTypeConnection.builder()
				       .edges(edges)
				       .pageInfo(pageInfo)
				       .build();
	}

	@SchemaMapping
	public PartyClassificationTypeConnection children(PartyClassificationType parent, @Argument PageInfo pageInfo) {
		final List<Edge<PartyClassificationType>> edges = repository.findPartyClassificationTypesByParentId(parent.getId(), pageInfoToPageable(pageInfo)).stream()
				                                                  .map(node -> PartyClassificationTypeEdge.builder()
						                                                               .node(node)
						                                                               .cursor(Cursor.builder().value(valueOf(node.getId().hashCode())).build())
						                                                               .build())
				                                                  .collect(Collectors.toList());
		return PartyClassificationTypeConnection.builder()
				       .edges(edges)
				       .pageInfo(pageInfo)
				       .build();
	}
}
