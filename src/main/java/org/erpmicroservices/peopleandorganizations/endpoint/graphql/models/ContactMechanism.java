package org.erpmicroservices.peopleandorganizations.endpoint.graphql.models;

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
public class ContactMechanism {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	private UUID id;

	private String endPoint;

	private String directions;

	@ManyToOne
	private ContactMechanismType contactMechanismType;
}
