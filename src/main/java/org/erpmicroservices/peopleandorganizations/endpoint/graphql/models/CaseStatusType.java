package org.erpmicroservices.peopleandorganizations.endpoint.graphql.models;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
public class CaseStatusType extends AbstractPersistable<UUID> {
 @NotBlank
 @NotNull
 private String description;

 @ManyToOne
 private CaseStatusType parent;

 public String getDescription() {
	return description;
 }

 public void setDescription(String description) {
	this.description = description;
 }

 public CaseStatusType getParent() {
	return parent;
 }

 public void setParent(CaseStatusType parent) {
	this.parent = parent;
 }
}
