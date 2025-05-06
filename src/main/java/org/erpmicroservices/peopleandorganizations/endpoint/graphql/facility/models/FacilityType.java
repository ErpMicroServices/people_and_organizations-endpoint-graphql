package org.erpmicroservices.peopleandorganizations.endpoint.graphql.facility.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.EntityType;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class FacilityType extends EntityType<FacilityType> {

}
