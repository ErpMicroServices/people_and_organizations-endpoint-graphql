package org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.name;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PartyName {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	private UUID id;

	@NotNull
	private String name;

	@NotNull
	private LocalDate fromDate;

	private LocalDate thruDate;

	@NotNull
	private UUID partyId;

	@NotNull
	private UUID nameTypeId;
}
