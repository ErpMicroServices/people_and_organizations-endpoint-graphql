package org.erpmicroservices.peopleandorganizations.endpoint.graphql.contactmechanism;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

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
    private UUID contactMechanismType;
    private List<UUID> geographicBoundaryIds = new ArrayList<>();
}
