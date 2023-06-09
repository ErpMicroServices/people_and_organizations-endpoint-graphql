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
		query = "select cast(geographic_boundary.id as varchar) as id, geographic_boundary.geo_code, geographic_boundary.name,\n" +
				        "       geographic_boundary.abbreviation, cast(geographic_boundary.geographic_boundary_type_id as varchar)\n" +
				        "from geographic_boundary, contact_mechanism_geographic_boundary\n" +
				        "where\n" +
				        "    contact_mechanism_geographic_boundary.contact_mechanism_id = :contactMechanismId\n" +
				        "and\n" +
				        "    geographic_boundary.id = contact_mechanism_geographic_boundary.geographic_boundary_id\n",
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
