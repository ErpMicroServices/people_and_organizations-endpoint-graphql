package org.erpmicroservices.peopleandorganizations.endpoint.graphql.facility.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.contactmechanism.models.ContactMechanism;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Facility {
	private FacilityType facilityTypeId;
	private String description;
	private Facility partOf;
	private Long squareFootage;
	private List<ContactMechanism> contactMechanisms;
	private List<FacilityRole> roles;
}
