package org.erpmicroservices.peopleandorganizations.backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "name_type")
public class NameTypeEntity extends AbstractType<NameTypeEntity> {
}
