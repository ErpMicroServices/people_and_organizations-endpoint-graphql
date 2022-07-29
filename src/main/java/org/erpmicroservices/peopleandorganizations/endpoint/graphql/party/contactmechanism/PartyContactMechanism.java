package org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.contactmechanism;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "party_contact_mechanism")
public class PartyContactMechanism {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	private UUID id;

	private LocalDate fromDate;

	private LocalDate thruDate;

	private boolean doNotSolicitIndicator;

	private String comment;

	@NotNull
	private UUID partyId;

	@NotNull
	private UUID contactMechanismId;

	@NotNull
	private UUID partyContactMechanismPurposeId;

}
