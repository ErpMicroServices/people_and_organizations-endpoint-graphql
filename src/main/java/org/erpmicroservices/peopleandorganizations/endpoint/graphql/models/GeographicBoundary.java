package org.erpmicroservices.peopleandorganizations.endpoint.graphql.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
	@ManyToOne
	private GeographicBoundaryType geographicBoundaryType;
}
