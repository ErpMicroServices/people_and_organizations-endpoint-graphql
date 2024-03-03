package org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.facility.models.FacilityType;

import java.util.List;
import java.util.UUID;

@Data
@SuperBuilder
public class EntityType<T>{
    private UUID id;
    private String description;
    private T parent;
    private List<T> children;
}
