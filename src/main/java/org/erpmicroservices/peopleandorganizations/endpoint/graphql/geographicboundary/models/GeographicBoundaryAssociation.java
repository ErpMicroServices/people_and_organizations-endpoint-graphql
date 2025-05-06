package org.erpmicroservices.peopleandorganizations.endpoint.graphql.geographicboundary.models;

import lombok.Builder;
import lombok.Data;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.geographicboundary.GeographicBoundary;

import java.util.UUID;

@Data
@Builder
public class GeographicBoundaryAssociation {
    private UUID id;
    private GeographicBoundary withinBoundary;
    private GeographicBoundary inBoundary;
}
