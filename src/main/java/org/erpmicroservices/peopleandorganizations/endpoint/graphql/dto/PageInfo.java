package org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto;

import graphql.relay.ConnectionCursor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PageInfo implements graphql.relay.PageInfo {

	private ConnectionCursor startCursor;
	private ConnectionCursor endCursor;
	private boolean hasNextPage;
	private boolean hasPreviousPage;

	/**
	 * @return cursor to the first edge, or null if this page is empty.
	 */
	@Override
	public ConnectionCursor getStartCursor() {
		return startCursor;
	}

	/**
	 * @return cursor to the last edge, or null if this page is empty.
	 */
	@Override
	public ConnectionCursor getEndCursor() {
		return endCursor;
	}

	/**
	 * @return true if and only if this page is not the first page. only meaningful when you gave the {@code last} argument.
	 */
	@Override
	public boolean isHasPreviousPage() {
		return false;
	}

	/**
	 * @return true if and only if this page is not the last page. only meaningful when you gave the {@code first} argument.
	 */
	@Override
	public boolean isHasNextPage() {
		return false;
	}
}
