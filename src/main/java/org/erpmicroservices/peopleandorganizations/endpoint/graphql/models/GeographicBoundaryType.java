package org.erpmicroservices.peopleandorganizations.endpoint.graphql.models;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
public class GeographicBoundaryType extends AbstractType<GeographicBoundaryType> {
}
