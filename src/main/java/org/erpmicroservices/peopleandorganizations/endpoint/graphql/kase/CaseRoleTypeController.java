package org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase;

import graphql.relay.Edge;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.Cursor;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.PageInfo;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.CaseRoleTypeRepository;
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
public class CaseRoleTypeController {

	private final CaseRoleTypeRepository caseRoleTypeRepository;

	public CaseRoleTypeController(final CaseRoleTypeRepository caseRoleTypeRepository) {
		this.caseRoleTypeRepository = caseRoleTypeRepository;
	}

	@QueryMapping
	public CaseRoleTypeConnection caseRoleTypes(@Argument PageInfo pageInfo) {
		final Page<CaseRoleType> caseRoleTypePage = caseRoleTypeRepository.findCaseRoleTypesByParentIsNull(pageInfoToPageable(pageInfo));
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

	@SchemaMapping
	public CaseRoleTypeConnection children(CaseRoleType parent, @Argument PageInfo pageInfo) {
		final Page<CaseRoleType> caseRoleTypePage = caseRoleTypeRepository.findCaseRoleTypesByParentEquals(parent, pageInfoToPageable(pageInfo));
		final List<Edge<CaseRoleType>> caseRoleTypeEdges = caseRoleTypePage.stream()
				                                                   .map(caseRoleType -> CaseRoleTypeEdge.builder()
						                                                                        .cursor(Cursor.builder().value(valueOf(caseRoleType.getId().hashCode())).build())
						                                                                        .node(caseRoleType)
						                                                                        .build())
				                                                   .collect(Collectors.toList());
		return CaseRoleTypeConnection.builder()
				       .edges(caseRoleTypeEdges)
				       .pageInfo(pageInfo)
				       .build();
	}
}
