package org.erpmicroservices.peopleandorganizations.endpoint.graphql.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "communication_event")
public class CommunicationEvent extends AbstractPersistable<UUID> {
 @Column(name = "started", columnDefinition = "DATE")
 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
 @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
 private LocalDateTime started;

 @Column(name = "ended", columnDefinition = "DATE")
 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
 @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
 private LocalDateTime ended;

 private String note;

 @ManyToOne
 private ContactMechanismType contactMechanismType;

 @ManyToOne
 @JoinColumn(name = "party_relationship_id")
 private PartyRelationship relationship;

 @ManyToOne
 @JoinColumn(name = "communication_event_status_type_id")
 private CommunicationEventStatusType statusType;

 @ManyToOne
 @JoinColumn(name = "communication_event_type_id")
 private CommunicationEventType type;

 @ManyToOne
 @JoinColumn(name = "case_id")
 private Case aCase;

 @OneToMany
 @JoinColumn(name = "communication_event_id")
 private List<CommunicationEventPurpose> purposes = new ArrayList<>();

 @OneToMany
 @JoinColumn(name = "communication_event_id")
 private List<CommunicationEventRole> roles = new ArrayList<>();

 @OneToMany
 @JoinColumn(name = "communication_event_id")
 private List<CommunicationEventWorkEffort> effort = new ArrayList<>();

 public LocalDateTime getStarted() {
	return started;
 }

 public void setStarted(LocalDateTime started) {
	this.started = started;
 }

 public LocalDateTime getEnded() {
	return ended;
 }

 public void setEnded(LocalDateTime ended) {
	this.ended = ended;
 }

 public String getNote() {
	return note;
 }

 public void setNote(String note) {
	this.note = note;
 }

 public ContactMechanismType getContactMechanismType() {
	return contactMechanismType;
 }

 public void setContactMechanismType(ContactMechanismType contactMechanismType) {
	this.contactMechanismType = contactMechanismType;
 }

 public CommunicationEventStatusType getStatusType() {
	return statusType;
 }

 public void setStatusType(CommunicationEventStatusType statusType) {
	this.statusType = statusType;
 }

 public CommunicationEventType getType() {
	return type;
 }

 public void setType(CommunicationEventType type) {
	this.type = type;
 }


 public Case getaCase() {
	return aCase;
 }

 public void setaCase(Case aCase) {
	this.aCase = aCase;
 }

 public PartyRelationship getRelationship() {
	return relationship;
 }

 public void setRelationship(PartyRelationship relationship) {
	this.relationship = relationship;
 }

 public List<CommunicationEventPurpose> getPurposes() {
	return purposes;
 }

 public void setPurposes(List<CommunicationEventPurpose> purposes) {
	this.purposes = purposes;
 }

 public List<CommunicationEventRole> getRoles() {
	return roles;
 }

 public void setRoles(List<CommunicationEventRole> roles) {
	this.roles = roles;
 }

 public List<CommunicationEventWorkEffort> getEffort() {
	return effort;
 }

 public void setEffort(List<CommunicationEventWorkEffort> effort) {
	this.effort = effort;
 }
}
