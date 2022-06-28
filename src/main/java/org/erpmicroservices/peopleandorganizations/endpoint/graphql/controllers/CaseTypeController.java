package org.erpmicroservices.peopleandorganizations.endpoint.graphql.controllers;

import graphql.relay.Edge;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.Cursor;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.PageInfo;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.CaseType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.CaseTypeConnection;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.CaseTypeEdge;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.CaseTypeRepository;
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
public class CaseTypeController {

	private final CaseTypeRepository caseTypeRepository;

	public CaseTypeController(final CaseTypeRepository caseTypeRepository) {
		this.caseTypeRepository = caseTypeRepository;
	}

	@QueryMapping
	public CaseTypeConnection caseTypes(@Argument PageInfo pageInfo) {
		final Page<CaseType> caseTypePage = caseTypeRepository.findCaseTypeByParentIsNull(pageInfoToPageable(pageInfo));
		final List<Edge<CaseType>> caseTypeEdges = caseTypePage.stream()
				                                           .map(caseType -> CaseTypeEdge.builder()
						                                                            .node(caseType)
						                                                            .cursor(Cursor.builder().value(valueOf(caseType.getId().hashCode())).build())
						                                                            .build())
				                                           .collect(Collectors.toList());
		return CaseTypeConnection.builder()
				       .edges(caseTypeEdges)
				       .pageInfo(pageInfo)
				       .build();
	}

	@SchemaMapping
	public CaseTypeConnection children(@Argument PageInfo pageInfo, CaseType parent) {
		final Page<CaseType> caseTypeByChildren = caseTypeRepository.findCaseTypeByParentEquals(parent, pageInfoToPageable(pageInfo));
		final List<Edge<CaseType>> caseTypeEdges = caseTypeByChildren.stream()
				                                           .map(caseType -> CaseTypeEdge.builder()
						                                                            .cursor(Cursor.builder().value(valueOf(caseType.getId())).build())
						                                                            .node(caseType)
						                                                            .build())
				                                           .collect(Collectors.toList());
		return CaseTypeConnection.builder()
				       .edges(caseTypeEdges)
				       .pageInfo(pageInfo).build();
	}
}
