package org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommunicationEventPurpose {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	private UUID id;

	@ManyToOne
	private CommunicationEvent communicationEvent;

	@ManyToOne
	private CommunicationEventPurposeType communicationEventPurposeType;

	private String description;

}
