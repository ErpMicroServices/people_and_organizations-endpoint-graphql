package org.erpmicroservices.peopleandorganizations.endpoint.graphql.party;

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
@Table(name = "party_contact_mechanism_purpose")
public class PartyContactMechanismPurpose {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	private UUID id;

	private LocalDate fromDate;

	private LocalDate thruDate;

	@ManyToOne
	@JoinColumn(name = "contact_mechanism_purpose_type_id")
	private PartyContactMechanismPurposeType purposeType;
}
