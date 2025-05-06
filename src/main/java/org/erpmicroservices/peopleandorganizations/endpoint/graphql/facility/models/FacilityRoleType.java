package org.erpmicroservices.peopleandorganizations.endpoint.graphql.facility.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.EntityType;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class FacilityRoleType extends EntityType<FacilityRoleType> {
}
