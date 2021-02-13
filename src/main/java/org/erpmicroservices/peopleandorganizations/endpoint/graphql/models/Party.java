package org.erpmicroservices.peopleandorganizations.endpoint.graphql.models;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Party extends AbstractPersistable<UUID> {

 @ManyToOne
 @JoinColumn(name = "party_type_id")
 private PartyType type;

 @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
 @JoinColumn(name = "party_id")
 private List<PartyClassification> classifications = new ArrayList<>();

 @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
 @JoinColumn(name = "party_id")
 private List<PartyRole> roles = new ArrayList<>();

 @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
 @JoinColumn(name = "party_id")
 private List<PartyContactMechanism> contactMechanisms = new ArrayList<>();

 @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
 @JoinColumn(name = "party_id")
 private List<PartyName> names = new ArrayList<>();

 @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
 @JoinColumn(name = "party_id")
 private List<PartyName> identifications = new ArrayList<>();

 public List<PartyName> getNames() {
	return names;
 }

 public void setNames(List<PartyName> names) {
	this.names = names;
 }

 public List<PartyName> getIdentifications() {
	return identifications;
 }

 public void setIdentifications(List<PartyName> identifications) {
	this.identifications = identifications;
 }

 public PartyType getType() {
	return type;
 }

 public void setType(PartyType type) {
	this.type = type;
 }

 public List<PartyClassification> getClassifications() {
	return classifications;
 }

 public void setClassifications(List<PartyClassification> classifications) {
	this.classifications = classifications;
 }

 public List<PartyRole> getRoles() {
	return roles;
 }

 public void setRoles(List<PartyRole> roles) {
	this.roles = roles;
 }

 public List<PartyContactMechanism> getContactMechanisms() {
	return contactMechanisms;
 }

 public void setContactMechanisms(List<PartyContactMechanism> contactMechanisms) {
	this.contactMechanisms = contactMechanisms;
 }

}
