package org.erpmicroservices.peopleandorganizations.endpoint.graphql.party;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.type.PartyType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Party {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	private UUID id;

	private String comment;

	@ManyToOne
	private PartyType partyType;
}
