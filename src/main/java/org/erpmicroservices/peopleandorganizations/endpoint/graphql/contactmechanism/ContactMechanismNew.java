package org.erpmicroservices.peopleandorganizations.endpoint.graphql.contactmechanism;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class ContactMechanismNew {
	@NotNull
	private String endPoint;
	private String directions;
	@NotNull
	private UUID contactMechanismTypeId;
	private List<UUID> geographicBoundaryIds = new ArrayList<>();
}
