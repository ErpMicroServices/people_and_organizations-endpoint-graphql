package org.erpmicroservices.peopleandorganizations.endpoint.graphql.models;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class ContactMechanismType extends AbstractPersistable<UUID> {
 @OneToMany
 @JoinColumn(name = "contact_mechanism_type_id")
 private final List<ValidContactMechanismRole> validContactMechanismRoles = new ArrayList<>();
 @NotBlank
 @NotNull
 private String description;
 @ManyToOne
 private ContactMechanismType parent;

 public String getDescription() {
	return description;
 }

 public void setDescription(String description) {
	this.description = description;
 }

 public ContactMechanismType getParent() {
	return parent;
 }

 public void setParent(ContactMechanismType parent) {
	this.parent = parent;
 }
}
