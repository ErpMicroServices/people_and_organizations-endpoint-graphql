package org.erpmicroservices.peopleandorganizations.endpoint.graphql.facility;

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
public class FacilityRole {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	private UUID id;

	@NotNull
	private UUID facilityId;

	@NotNull
	private UUID facilityRoleTypeId;

	private UUID partyId;

	private LocalDate fromDate;

	private LocalDate thruDate;

}
