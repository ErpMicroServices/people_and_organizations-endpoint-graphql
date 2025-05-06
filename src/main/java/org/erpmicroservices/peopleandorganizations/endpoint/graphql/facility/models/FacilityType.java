package org.erpmicroservices.peopleandorganizations.endpoint.graphql.facility.models;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.EntityType;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class FacilityType extends EntityType<FacilityType> {

}
