package org.erpmicroservices.peopleandorganizations.endpoint.graphql.models;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity(name = "facility_contact_mechanism")
public class FacilityContactMechanism extends AbstractPersistable<UUID> {

 @ManyToOne
 @JoinColumn(name = "facility_id")
 private Facility facility;

 @ManyToOne
 @JoinColumn(name = "contact_mechanism_id")
 private ContactMechanism contactMechanism;

 public Facility getFacility() {
	return facility;
 }

 public void setFacility(Facility facility) {
	this.facility = facility;
 }

 public ContactMechanism getContactMechanism() {
	return contactMechanism;
 }

 public void setContactMechanism(ContactMechanism contactMechanism) {
	this.contactMechanism = contactMechanism;
 }
}
