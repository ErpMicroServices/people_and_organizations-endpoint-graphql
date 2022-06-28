package org.erpmicroservices.peopleandorganizations.endpoint.graphql.controllers;

import graphql.relay.Edge;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.CaseStatusTypeConnection;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.CaseStatusTypeEdge;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.Cursor;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.PageInfo;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.CaseStatusType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.CaseStatusTypeRepository;
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
public class CaseStatusTypeController {

	private final CaseStatusTypeRepository caseStatusTypeRepository;

	public CaseStatusTypeController(final CaseStatusTypeRepository caseStatusTypeRepository) {
		this.caseStatusTypeRepository = caseStatusTypeRepository;
	}

	@QueryMapping
	public CaseStatusTypeConnection caseStatusTypes(@Argument PageInfo pageInfo) {
		final Page<CaseStatusType> caseStatusTypePage = caseStatusTypeRepository.findCaseStatusTypeByParentIsNull(pageInfoToPageable(pageInfo));
		final List<Edge<CaseStatusType>> caseStatusTypeEdges = caseStatusTypePage.stream()
				                                                       .map(caseStatusType -> CaseStatusTypeEdge.builder()
						                                                                              .node(caseStatusType)
						                                                                              .cursor(Cursor.builder().value(valueOf(caseStatusType.getId().hashCode())).build())
						                                                                              .build())
				                                                       .collect(Collectors.toList());
		return CaseStatusTypeConnection.builder()
				       .edges(caseStatusTypeEdges)
				       .pageInfo(pageInfo)
				       .build();
	}

	@SchemaMapping
	public CaseStatusTypeConnection children(@Argument PageInfo pageInfo, CaseStatusType parent) {
		final Page<CaseStatusType> caseStatusTypeByChildren = caseStatusTypeRepository.findCaseStatusTypeByParentEquals(parent, pageInfoToPageable(pageInfo));
		final List<Edge<CaseStatusType>> caseStatusTypeEdges = caseStatusTypeByChildren.stream()
				                                                       .map(caseStatusType -> CaseStatusTypeEdge.builder()
						                                                                              .cursor(Cursor.builder().value(valueOf(caseStatusType.getId())).build())
						                                                                              .node(caseStatusType)
						                                                                              .build())
				                                                       .collect(Collectors.toList());
		return CaseStatusTypeConnection.builder()
				       .edges(caseStatusTypeEdges)
				       .pageInfo(pageInfo).build();
	}
}
