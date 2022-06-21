package org.erpmicroservices.peopleandorganizations.endpoint.graphql.models;

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
public class FacilityRole {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	private UUID id;

	private LocalDate fromDate;

	private LocalDate thruDate;

	@ManyToOne
	private Facility facility;

	@ManyToOne
	private FacilityRoleType facilityRoleType;

	@ManyToOne
	private Party party;
}
