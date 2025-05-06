package org.erpmicroservices.peopleandorganizations.backend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "valid_contact_mechanism_role")
public class ValidContactMechanismRoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private UUID id;

    @NotNull
    private UUID contactMechanismTypeId;

    @NotNull
    private UUID communicationEventRoleTypeId;
}
