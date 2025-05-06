package org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.contactmechanism;

import graphql.relay.Edge;
import org.erpmicroservices.peopleandorganizations.backend.entities.PartyContactMechanismPurposeTypeEntity;
import org.erpmicroservices.peopleandorganizations.backend.repositories.PartyContactMechanismPurposeTypeRepository;
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
public class PartyContactMechanismPurposeTypeController {

	private final PartyContactMechanismPurposeTypeRepository repository;

	public PartyContactMechanismPurposeTypeController(final PartyContactMechanismPurposeTypeRepository repository) {
		this.repository = repository;
	}

	@QueryMapping
	public PartyContactMechanismPurposeTypeConnection partyContactMechanismPurposeTypes(@Argument PageInfo pageInfo) {
		final List<Edge<PartyContactMechanismPurposeTypeEntity>> edges = repository.findPartyContactMechanismPurposeTypesByParentIdIsNull(pageInfoToPageable(pageInfo)).stream()
				                                                           .map(node -> PartyContactMechanismPurposeTypeEdge.builder()
						                                                                        .node(node)
						                                                                        .cursor(Cursor.builder().value(valueOf(node.getId().hashCode())).build())
						                                                                        .build())
				                                                           .collect(Collectors.toList());
		return PartyContactMechanismPurposeTypeConnection.builder()
				       .edges(edges)
				       .pageInfo(pageInfo)
				       .build();
	}

	@SchemaMapping
	public PartyContactMechanismPurposeTypeConnection children(PartyContactMechanismPurposeTypeEntity parent, @Argument PageInfo pageInfo) {
		final List<Edge<PartyContactMechanismPurposeTypeEntity>> edges = repository.findPartyContactMechanismPurposeTypesByParentId(parent.getId(), pageInfoToPageable(pageInfo)).stream()
				                                                           .map(node -> PartyContactMechanismPurposeTypeEdge.builder()
						                                                                        .node(node)
						                                                                        .cursor(Cursor.builder().value(valueOf(node.getId().hashCode())).build())
						                                                                        .build())
				                                                           .collect(Collectors.toList());
		return PartyContactMechanismPurposeTypeConnection.builder()
				       .edges(edges)
				       .pageInfo(pageInfo)
				       .build();
	}
}
