package org.erpmicroservices.peopleandorganizations.backend.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

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
