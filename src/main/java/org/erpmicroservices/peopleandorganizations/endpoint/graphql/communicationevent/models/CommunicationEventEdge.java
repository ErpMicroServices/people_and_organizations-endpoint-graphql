package org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.models;

import graphql.relay.ConnectionCursor;
import graphql.relay.Edge;
import lombok.Builder;
import lombok.Data;
import org.erpmicroservices.peopleandorganizations.backend.entities.CommunicationEventEntity;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.Cursor;


@Data
@Builder
public class CommunicationEventEdge implements Edge<CommunicationEvent> {

	private CommunicationEvent node;

	private Cursor cursor;

}
