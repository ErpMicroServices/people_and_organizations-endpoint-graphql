package org.erpmicroservices.peopleandorganizations.endpoint.graphql.contactmechanism;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
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
public class ContactMechanismType extends AbstractType<ContactMechanismType> {
	@Builder
	public ContactMechanismType(@NotNull final UUID id, @NotBlank final String description, final UUID parentId) {
		super(id, description, parentId);
	}
}
