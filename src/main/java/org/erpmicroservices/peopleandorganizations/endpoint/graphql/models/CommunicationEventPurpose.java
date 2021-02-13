package org.erpmicroservices.peopleandorganizations.endpoint.graphql.models;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity(name = "communication_event_purpose")
public class CommunicationEventPurpose extends AbstractPersistable<UUID> {
 @NotBlank
 @NotNull
 private String description;

 @ManyToOne
 @JoinColumn(name = "communication_event_purpose_type_id")
 private CommunicationEventPurposeType type;

 public String getDescription() {
	return description;
 }

 public void setDescription(String description) {
	this.description = description;
 }

 public CommunicationEventPurposeType getType() {
	return type;
 }

 public void setType(CommunicationEventPurposeType type) {
	this.type = type;
 }
}
