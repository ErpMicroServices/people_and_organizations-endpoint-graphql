package org.erpmicroservices.peopleandorganizations.endpoint.graphql.contactmechanism;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.geographicboundary.GeographicBoundary;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "contact_mechanism")
public class ContactMechanism {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	private UUID id;

	@Column(name = "end_point")
	private String endPoint;

	private String directions;

	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	private ContactMechanismType contactMechanismType;

	@ManyToMany
	@JoinTable(
			name = "contact_mechanism_geographic_boundary",
			joinColumns = @JoinColumn(name = "contact_mechanism_id"),
			inverseJoinColumns = @JoinColumn(name = "geographic_boundary_id"))
	private List<GeographicBoundary> geographicBoundaries;
}
