package org.erpmicroservices.peopleandorganizations.endpoint.graphql.models;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity(name = "communication_event_role")
public class CommunicationEventRole extends AbstractPersistable<UUID> {
 @ManyToOne
 @JoinColumn(name = "communication_event_role_type_id")
 private CommunicationEventRoleType type;

 @ManyToOne
 @JoinColumn(name = "party_id")
 private Party party;

 public CommunicationEventRoleType getType() {
	return type;
 }

 public void setType(CommunicationEventRoleType type) {
	this.type = type;
 }

 public Party getParty() {
	return party;
 }

 public void setParty(Party party) {
	this.party = party;
 }
}
