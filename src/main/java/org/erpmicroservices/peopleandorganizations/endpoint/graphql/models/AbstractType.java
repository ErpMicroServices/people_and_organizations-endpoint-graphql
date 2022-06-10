package org.erpmicroservices.peopleandorganizations.endpoint.graphql.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@MappedSuperclass
@Data
public class AbstractType<T> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	private UUID id;

	@NotBlank
	private String description;

	@ManyToOne
	private T parent;
	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
	private List<T> children;
}
