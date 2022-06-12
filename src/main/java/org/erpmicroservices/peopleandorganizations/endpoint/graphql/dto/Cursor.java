package org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto;

import graphql.relay.ConnectionCursor;
import lombok.Builder;
import lombok.Data;

import java.util.Base64;

@Data
@Builder
public class Cursor implements ConnectionCursor {

	private String value;

	/**
	 * @return an opaque string that represents this cursor.
	 */
	@Override
	public String getValue() {
		return Base64.getEncoder().encodeToString(value.getBytes());
	}
}
