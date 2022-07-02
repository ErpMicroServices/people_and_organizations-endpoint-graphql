package org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.classification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
public class PartyClassification {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	private UUID id;

	@ManyToOne
	private Party party;

	@ManyToOne
	private PartyClassificationType partyClassificationType;

	private String value;

	private LocalDate fromDate;

	private LocalDate thruDate;
}
