package org.erpmicroservices.peopleandorganizations.endpoint.graphql.geographicboundary;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@NamedNativeQuery(name = "GeographicBoundary.findByContactMechanismId",
		query = "select geographic_boundary.id, " +
					"geographic_boundary.geo_code, " +
					"geographic_boundary.name, " +
					"geographic_boundary.abbreviation, " +
					"geographic_boundary.geographic_boundary_type_id " +
				"from geographic_boundary, contact_mechanism_geographic_boundary " +
				"where " +
					"contact_mechanism_geographic_boundary.contact_mechanism_id = :contactMechanismId " +
					"and " +
					"geographic_boundary.id = contact_mechanism_geographic_boundary.geographic_boundary_id ",
		resultClass = GeographicBoundary.class)
public class GeographicBoundary {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	private UUID id;

	@NotNull
	@NotEmpty
	private String name;
	private String geoCode;
	private String abbreviation;

	@NotNull
	private UUID geographicBoundaryTypeId;
}
