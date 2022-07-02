package org.erpmicroservices.peopleandorganizations.endpoint.graphql.geographicboundary;

import graphql.relay.Edge;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.Cursor;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.PageInfo;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.GeographicBoundaryRepository;
import org.springframework.data.domain.Page;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.valueOf;
import static org.erpmicroservices.peopleandorganizations.endpoint.graphql.misc.Utils.pageInfoToPageable;

@Controller
public class GeographicBoundaryController {

	private final GeographicBoundaryRepository repository;

	public GeographicBoundaryController(final GeographicBoundaryRepository repository) {
		this.repository = repository;
	}

	@QueryMapping
	public GeographicBoundaryConnection geographicBoundaries(@Argument PageInfo pageInfo) {
		final Page<GeographicBoundary> geographicBoundaries = repository.findAll(pageInfoToPageable(pageInfo));
		final List<Edge<GeographicBoundary>> geographicBoundaryEdges = geographicBoundaries.get()
				                                                               .map(geographicBoundary -> GeographicBoundaryEdge.builder()
						                                                                                          .node(geographicBoundary)
						                                                                                          .cursor(Cursor.builder().value(valueOf(geographicBoundary.getId().hashCode())).build())
						                                                                                          .build())
				                                                               .collect(Collectors.toList());
		return GeographicBoundaryConnection.builder()
				       .edges(geographicBoundaryEdges)
				       .pageInfo(pageInfo)
				       .build();
	}


}
