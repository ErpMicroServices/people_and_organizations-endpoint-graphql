package org.erpmicroservices.peopleandorganizations.endpoint.graphql.contactmechanism;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

	@NotNull
	private UUID contactMechanismTypeId;

}
