package org.erpmicroservices.peopleandorganizations.endpoint.graphql.name;

import graphql.relay.Edge;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.Cursor;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.PageInfo;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.NameTypeRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.valueOf;
import static org.erpmicroservices.peopleandorganizations.endpoint.graphql.misc.Utils.pageInfoToPageable;


@Controller
public class NameTypeController {

	private final NameTypeRepository repository;

	public NameTypeController(final NameTypeRepository repository) {
		this.repository = repository;
	}

	@QueryMapping
	public NameTypeConnection nameTypes(@Argument PageInfo pageInfo) {
		final List<Edge<NameType>> edges = repository.findNameTypesByParentIsNull(pageInfoToPageable(pageInfo)).stream()
				                                   .map(node -> NameTypeEdge.builder()
						                                                .node(node)
						                                                .cursor(Cursor.builder().value(valueOf(node.getId().hashCode())).build())
						                                                .build())
				                                   .collect(Collectors.toList());
		return NameTypeConnection.builder()
				       .edges(edges)
				       .pageInfo(pageInfo)
				       .build();
	}

	@SchemaMapping
	public NameTypeConnection children(NameType parent, @Argument PageInfo pageInfo) {
		final List<Edge<NameType>> edges = repository.findNameTypesByParentEquals(parent, pageInfoToPageable(pageInfo)).stream()
				                                   .map(node -> NameTypeEdge.builder()
						                                                .node(node)
						                                                .cursor(Cursor.builder().value(valueOf(node.getId().hashCode())).build())
						                                                .build())
				                                   .collect(Collectors.toList());
		return NameTypeConnection.builder()
				       .edges(edges)
				       .pageInfo(pageInfo)
				       .build();
	}
}
