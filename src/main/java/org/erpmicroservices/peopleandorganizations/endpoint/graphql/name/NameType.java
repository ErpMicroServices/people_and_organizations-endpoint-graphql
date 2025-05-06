package org.erpmicroservices.peopleandorganizations.endpoint.graphql.name;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.erpmicroservices.peopleandorganizations.backend.entities.AbstractType;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "name_type")
public class NameType extends AbstractType<NameType> {
}
