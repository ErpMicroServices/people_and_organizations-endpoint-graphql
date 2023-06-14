package org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.AbstractType;

import java.util.UUID;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class CommunicationEventPurposeType extends AbstractType<CommunicationEventPurposeType> {
	@Builder
	public CommunicationEventPurposeType(@NotNull final UUID id, @NotEmpty final String description, final UUID parentId) {
		super(id, description, parentId);
	}
}
