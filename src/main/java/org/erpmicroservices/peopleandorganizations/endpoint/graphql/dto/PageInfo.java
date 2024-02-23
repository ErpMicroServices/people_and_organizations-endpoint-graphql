package org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto;

import graphql.relay.ConnectionCursor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.List;

@Data
@Builder
public class PageInfo implements graphql.relay.PageInfo {

	@Builder.Default
	private int pageSize = 100;
	@Builder.Default
	private int pageNumber = 0;
	@Builder.Default
	private List<String> sortBy = Collections.emptyList();
	@Builder.Default
	private Sort.Direction sortDirection = Sort.DEFAULT_DIRECTION;

	@Override
	public ConnectionCursor getStartCursor() {
		return null;
	}

	@Override
	public ConnectionCursor getEndCursor() {
		return null;
	}

	@Override
	public boolean isHasPreviousPage() {
		return false;
	}

	@Override
	public boolean isHasNextPage() {
		return false;
	}
}
