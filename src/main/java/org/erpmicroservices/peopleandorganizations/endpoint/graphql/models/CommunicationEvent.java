package org.erpmicroservices.peopleandorganizations.endpoint.graphql.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Data
public class CommunicationEvent {
	@Id
	@NotNull
	private UUID id;
}
