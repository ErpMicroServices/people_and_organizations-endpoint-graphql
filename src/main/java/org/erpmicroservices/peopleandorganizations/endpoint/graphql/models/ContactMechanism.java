package org.erpmicroservices.peopleandorganizations.endpoint.graphql.models;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity(name = "contact_mechanism")
public class ContactMechanism extends AbstractPersistable<UUID> {
 @NotBlank
 @NotNull
 private String endPoint;

 private String directions;

 @ManyToOne
 @JoinColumn(name = "contact_mechanism_type_id")
 private ContactMechanismType type;

 public String getEndPoint() {
	return endPoint;
 }

 public void setEndPoint(String endPoint) {
	this.endPoint = endPoint;
 }

 public String getDirections() {
	return directions;
 }

 public void setDirections(String directions) {
	this.directions = directions;
 }

 public ContactMechanismType getType() {
	return type;
 }

 public void setType(ContactMechanismType type) {
	this.type = type;
 }

}
