package org.erpmicroservices.peopleandorganizations.backend.entities;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
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
public class CommunicationEventPurposeTypeEntity extends AbstractType<CommunicationEventPurposeTypeEntity> {
	@Builder
	public CommunicationEventPurposeTypeEntity(@NotNull final UUID id, @NotEmpty final String description, final UUID parentId) {
		super(id, description, parentId);
	}
}
