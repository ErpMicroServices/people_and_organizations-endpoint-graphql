package org.erpmicroservices.peopleandorganizations.endpoint.graphql.controllers;

import graphql.relay.Edge;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.CaseRoleTypeConnection;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.CaseRoleTypeEdge;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.Cursor;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.PageInfo;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.CaseRoleType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.CaseRoleTypeRepository;
import org.springframework.data.domain.Page;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.valueOf;
import static org.erpmicroservices.peopleandorganizations.endpoint.graphql.misc.Utils.pageInfoToPageable;

@Controller
public class CaseRoleTypeController {

	private final CaseRoleTypeRepository caseRoleTypeRepository;

	public CaseRoleTypeController(final CaseRoleTypeRepository caseRoleTypeRepository) {
		this.caseRoleTypeRepository = caseRoleTypeRepository;
	}

	@QueryMapping
	public CaseRoleTypeConnection caseRoleTypes(PageInfo pageInfo) {
		final Page<CaseRoleType> caseRoleTypePage = caseRoleTypeRepository.findAll(pageInfoToPageable(pageInfo));
		final List<Edge<CaseRoleType>> caseRoleTypeEdges = caseRoleTypePage.stream()
				                                                   .map(caseRoleType -> CaseRoleTypeEdge.builder()
						                                                                        .node(caseRoleType)
						                                                                        .cursor(Cursor.builder()
								                                                                                .value(valueOf(caseRoleType.hashCode())).build())
						                                                                        .build())
				                                                   .collect(Collectors.toList());
		return CaseRoleTypeConnection.builder()
				       .edges(caseRoleTypeEdges)
				       .pageInfo(pageInfo)
				       .build();
	}

}
