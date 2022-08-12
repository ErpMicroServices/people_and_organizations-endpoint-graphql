package org.erpmicroservices.peopleandorganizations.endpoint.graphql.facility;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.AbstractType;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class FacilityType extends AbstractType<FacilityType> {
	@Builder

	public FacilityType(@NotNull final UUID id, @NotBlank final String description, final UUID parentId) {
		super(id, description, parentId);
	}
}
