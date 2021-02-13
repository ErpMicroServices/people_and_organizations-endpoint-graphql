package org.erpmicroservices.peopleandorganizations.endpoint.graphql.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "party_relationship")
public class PartyRelationship extends AbstractPersistable<UUID> {

 @Column(name = "from_date", columnDefinition = "DATE")
 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
 @JsonFormat(pattern = "yyyy-MM-dd")
 private LocalDate fromDate;

 @Column(name = "thru_date", columnDefinition = "DATE")
 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
 @JsonFormat(pattern = "yyyy-MM-dd")
 private LocalDate thruDate;

 private String comment;

 @ManyToOne
 private PartyRole fromPartyRole;

 @ManyToOne
 private PartyRole toPartyRole;

 @ManyToOne
 @JoinColumn(name = "party_relationship_type_id")
 private PartyRelationshipType type;

 @ManyToOne
 @JoinColumn(name = "priority_type_id")
 private PriorityType priority;

 @OneToMany
 @JoinColumn(name = "party_relationship_id")
 private List<CommunicationEvent> communicationEvents = new ArrayList<>();

 public LocalDate getFromDate() {
	return fromDate;
 }

 public void setFromDate(LocalDate fromDate) {
	this.fromDate = fromDate;
 }

 public LocalDate getThruDate() {
	return thruDate;
 }

 public void setThruDate(LocalDate thruDate) {
	this.thruDate = thruDate;
 }

 public String getComment() {
	return comment;
 }

 public void setComment(String comment) {
	this.comment = comment;
 }

 public PartyRole getFromPartyRole() {
	return fromPartyRole;
 }

 public void setFromPartyRole(PartyRole fromPartyRole) {
	this.fromPartyRole = fromPartyRole;
 }

 public PartyRole getToPartyRole() {
	return toPartyRole;
 }

 public void setToPartyRole(PartyRole toPartyRole) {
	this.toPartyRole = toPartyRole;
 }

 public PartyRelationshipType getType() {
	return type;
 }

 public void setType(PartyRelationshipType type) {
	this.type = type;
 }

 public PriorityType getPriority() {
	return priority;
 }

 public void setPriority(PriorityType priority) {
	this.priority = priority;
 }

 public List<CommunicationEvent> getCommunicationEvents() {
	return communicationEvents;
 }

 public void setCommunicationEvents(List<CommunicationEvent> communicationEvents) {
	this.communicationEvents = communicationEvents;
 }
}
