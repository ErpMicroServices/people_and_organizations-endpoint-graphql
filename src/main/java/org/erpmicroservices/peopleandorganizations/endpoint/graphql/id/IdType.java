package org.erpmicroservices.peopleandorganizations.endpoint.graphql.id;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.EntityType;

import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class IdType extends EntityType<IdType> {

}
