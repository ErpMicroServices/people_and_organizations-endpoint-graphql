package org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.relationship;

import graphql.relay.ConnectionCursor;
import graphql.relay.Edge;
import lombok.Builder;
import lombok.Data;
import org.erpmicroservices.peopleandorganizations.backend.entities.PartyRelationshipStatusTypeEntity;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.Cursor;


@Data
@Builder
public class PartyRelationshipStatusTypeEdge implements Edge<PartyRelationshipStatusTypeEntity> {

	private PartyRelationshipStatusTypeEntity node;

	private Cursor cursor;

	/**
	 * @return the node of data that this edge represents
	 */
	@Override
	public PartyRelationshipStatusTypeEntity getNode() {
		return node;
	}

	/**
	 * @return the cursor for this edge node
	 */
	@Override
	public ConnectionCursor getCursor() {
		return cursor;
	}
}
