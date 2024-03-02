package org.erpmicroservices.peopleandorganizations.backend.entities;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class PartyRelationshipStatusType extends AbstractType<PartyRelationshipStatusType> {

	@Builder

	public PartyRelationshipStatusType(@NotNull final UUID id, @NotBlank final String description, final UUID parentId) {
		super(id, description, parentId);
	}
}
