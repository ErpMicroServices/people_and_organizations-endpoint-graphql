package org.erpmicroservices.peopleandorganizations.endpoint.graphql.id;

import graphql.relay.Edge;
import org.erpmicroservices.peopleandorganizations.backend.entities.IdTypeEntity;
import org.erpmicroservices.peopleandorganizations.backend.repositories.IdTypeRepository;
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
public class IdTypeController {

    private final IdTypeRepository repository;

    public IdTypeController(final IdTypeRepository repository) {
        this.repository = repository;
    }

    @QueryMapping
    public IdTypeConnection idTypes(@Argument PageInfo pageInfo) {
        final List<Edge<IdType>> edges = repository.findIdTypesByParentIdIsNull(pageInfoToPageable(pageInfo)).stream()
                .map(node -> IdTypeEdge.builder()
                        .node(IdType.builder()
                                .id(node.getId())
                                .description(node.getDescription())
                                .build())
                        .cursor(Cursor.builder().value(valueOf(node.getId().hashCode())).build())
                        .build())
                .collect(Collectors.toList());
        return IdTypeConnection.builder()
                .edges(edges)
                .pageInfo(pageInfo)
                .build();
    }

    @SchemaMapping
    public IdTypeConnection children(IdType parent, @Argument PageInfo pageInfo) {
        final List<Edge<IdType>> edges = repository.findIdTypesByParentId(parent.getId(), pageInfoToPageable(pageInfo)).stream()
                .map(node -> IdTypeEdge.builder()
                        .node(IdType.builder()
                                .description(node.getDescription())
                                .id(node.getId())
                                .build())
                        .cursor(Cursor.builder().value(valueOf(node.getId().hashCode())).build())
                        .build())
                .collect(Collectors.toList());
        return IdTypeConnection.builder()
                .edges(edges)
                .pageInfo(pageInfo)
                .build();
    }
}
