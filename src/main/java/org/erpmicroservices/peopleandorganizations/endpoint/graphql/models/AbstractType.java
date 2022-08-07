package org.erpmicroservices.peopleandorganizations.endpoint.graphql.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@MappedSuperclass
@Data
@NoArgsConstructor
public class AbstractType<T> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	private UUID id;

	@NotBlank
	private String description;

	private UUID parentId;

	public AbstractType(final UUID id, final String description, final UUID parentId) {
		this.id = id;
		this.description = description;
		this.parentId = parentId;
	}
}
