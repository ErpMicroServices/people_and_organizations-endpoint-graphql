package org.erpmicroservices.peopleandorganizations.endpoint.graphql.geographicboundary;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class GeographicBoundary {
    private UUID id;
    private String geoCode;
    private String name;
    private String abbreviation;
    private GeographicBoundaryType geographicBoundaryType;
    private List<GeographicBoundary> within;
    private List<GeographicBoundary> inside;
}
