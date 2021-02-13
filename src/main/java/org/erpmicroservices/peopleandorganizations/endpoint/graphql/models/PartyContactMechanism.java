package org.erpmicroservices.peopleandorganizations.endpoint.graphql.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.UUID;

@Entity(name = "party_contact_mechanism")
public class PartyContactMechanism extends AbstractPersistable<UUID> {

 @Column(name = "from_date", columnDefinition = "DATE")
 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
 @JsonFormat(pattern = "yyyy-MM-dd")
 private LocalDate fromDate;

 @Column(name = "thru_date", columnDefinition = "DATE")
 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
 @JsonFormat(pattern = "yyyy-MM-dd")
 private LocalDate thruDate;

 private boolean doNotSolicitIndicator = true;

 private String comment;

 @ManyToOne
 @JoinColumn(name = "party_contact_mechanism_purpose_id")
 private PartyContactMechanismPurpose purpose;

 @ManyToOne
 @JoinColumn(name = "contact_mechanism_id")
 private ContactMechanism contactMechanism;

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

 public boolean isDoNotSolicitIndicator() {
	return doNotSolicitIndicator;
 }

 public void setDoNotSolicitIndicator(boolean doNotSolicitIndicator) {
	this.doNotSolicitIndicator = doNotSolicitIndicator;
 }

 public String getComment() {
	return comment;
 }

 public void setComment(String comment) {
	this.comment = comment;
 }

 public PartyContactMechanismPurpose getPurpose() {
	return purpose;
 }

 public void setPurpose(PartyContactMechanismPurpose purpose) {
	this.purpose = purpose;
 }

 public ContactMechanism getContactMechanism() {
	return contactMechanism;
 }

 public void setContactMechanism(ContactMechanism contactMechanism) {
	this.contactMechanism = contactMechanism;
 }
}
