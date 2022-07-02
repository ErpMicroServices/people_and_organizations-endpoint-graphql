package org.erpmicroservices.peopleandorganizations.endpoint.graphql.facility;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Facility {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	private UUID id;

	private String description;

	@ManyToOne
	private Facility partOf;

	@ManyToOne
	private FacilityType facilityType;

	private Long squareFootage;

	@OneToMany(mappedBy = "facility")
	private List<FacilityContactMechanism> contactMechanisms;
}
