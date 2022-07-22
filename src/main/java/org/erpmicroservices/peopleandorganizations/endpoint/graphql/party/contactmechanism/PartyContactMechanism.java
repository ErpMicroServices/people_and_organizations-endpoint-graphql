package org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.contactmechanism;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.contactmechanism.ContactMechanism;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.Party;

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

//	private UUID partyId;
//
//	private UUID contactMechanismId;
//
//	private UUID partyContactMechanismPurpose;
@ManyToOne
private Party party;

	@ManyToOne
	private ContactMechanism contactMechanism;

	@ManyToOne
	@JoinColumn(name = "party_contact_mechanism_purpose_id")
	private PartyContactMechanismPurpose purpose;

}
