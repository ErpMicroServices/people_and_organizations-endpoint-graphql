package org.erpmicroservices.peopleandorganizations.endpoint.graphql.geographicboundary;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.EntityType;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class GeographicBoundaryType extends EntityType<GeographicBoundaryType> {
}
