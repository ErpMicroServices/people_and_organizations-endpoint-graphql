package org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.name;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private UUID party;

    @NotNull
    private UUID nameType;
}
