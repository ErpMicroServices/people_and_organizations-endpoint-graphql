package org.erpmicroservices.peopleandorganizations.endpoint.graphql.party;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.name.NameType;

import javax.persistence.*;
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

	@ManyToOne
	@NotNull
	private Party party;

	@ManyToOne
	@NotNull
	private NameType nameType;
}
