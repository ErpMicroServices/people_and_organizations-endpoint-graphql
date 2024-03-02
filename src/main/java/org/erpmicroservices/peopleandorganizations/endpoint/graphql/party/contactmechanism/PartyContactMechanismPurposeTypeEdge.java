package org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.contactmechanism;

import graphql.relay.ConnectionCursor;
import graphql.relay.Edge;
import lombok.Builder;
import lombok.Data;
import org.erpmicroservices.peopleandorganizations.backend.entities.PartyContactMechanismPurposeType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.Cursor;


@Data
@Builder
public class PartyContactMechanismPurposeTypeEdge implements Edge<PartyContactMechanismPurposeType> {

	private PartyContactMechanismPurposeType node;

	private Cursor cursor;

	/**
	 * @return the node of data that this edge represents
	 */
	@Override
	public PartyContactMechanismPurposeType getNode() {
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
