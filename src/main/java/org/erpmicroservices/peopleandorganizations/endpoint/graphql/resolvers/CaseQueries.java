package org.erpmicroservices.peopleandorganizations.endpoint.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import graphql.relay.*;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.Case;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.relay.CursorBuilders;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.CaseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CaseQueries implements GraphQLQueryResolver {


	private final CaseRepo caseRepo;
	private final CursorBuilders cursorBuilders;

	@Autowired
	public CaseQueries(CaseRepo caseRepo, CursorBuilders cursorBuilders) {
		this.caseRepo = caseRepo;
		this.cursorBuilders = cursorBuilders;
	}

	public Connection<Case> cases(int first, @Nullable String cursor) {
		int skip = Integer.valueOf(cursor);
		final Page<Case> casePage = caseRepo.findAll(PageRequest.of(skip, first));
		final List<Edge<Case>> defaultEdgeList = casePage
				                                         .stream()
				                                         .map(c -> new DefaultEdge<Case>(c, cursorBuilders.from(c.getId())))
				                                         .collect(Collectors.toUnmodifiableList());

		return new DefaultConnection<Case>(defaultEdgeList,
				new DefaultPageInfo(cursorBuilders.firstCursorForm(defaultEdgeList),
						cursorBuilders.lastCursorForm(defaultEdgeList),
						casePage.hasPrevious(),
						casePage.hasNext()));
	}

}
