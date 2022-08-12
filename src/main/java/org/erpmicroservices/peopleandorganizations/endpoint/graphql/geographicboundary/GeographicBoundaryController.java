package org.erpmicroservices.peopleandorganizations.endpoint.graphql.geographicboundary;

import graphql.relay.Edge;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.Cursor;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.PageInfo;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.GeographicBoundaryRepository;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.GeographicBoundaryTypeRepository;
import org.springframework.data.domain.Page;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.valueOf;
import static org.erpmicroservices.peopleandorganizations.endpoint.graphql.misc.Utils.pageInfoToPageable;

@Controller
public class GeographicBoundaryController {

	private final GeographicBoundaryRepository geographicBoundaryRepository;

	private final GeographicBoundaryTypeRepository geographicBoundaryTypeRepository;

	public GeographicBoundaryController(final GeographicBoundaryRepository geographicBoundaryRepository, final GeographicBoundaryTypeRepository geographicBoundaryTypeRepository) {
		this.geographicBoundaryRepository = geographicBoundaryRepository;
		this.geographicBoundaryTypeRepository = geographicBoundaryTypeRepository;
	}

	@QueryMapping
	public GeographicBoundaryConnection geographicBoundaries(@Argument PageInfo pageInfo) {
		final Page<GeographicBoundary> geographicBoundaries = geographicBoundaryRepository.findAll(pageInfoToPageable(pageInfo));
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

	@SchemaMapping
	public GeographicBoundaryType geographicBoundaryType(GeographicBoundary geographicBoundary) {
		return geographicBoundaryTypeRepository.findById(geographicBoundary.getGeographicBoundaryTypeId()).orElseThrow();
	}

}
