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

@Entity(name = "facility")
public class Facility extends AbstractPersistable<UUID> {
 @NotBlank
 @NotNull
 private String description;

 private Long squareFootage;

 @ManyToOne
 @JoinColumn(name = "part_of")
 private Facility partOf;

 @OneToMany
 @JoinColumn(name = "part_of")
 private List<Facility> contains = new ArrayList<>();

 @ManyToOne
 @JoinColumn(name = "facility_type_id")
 private FacilityType type;

 @OneToMany
 @JoinColumn(name = "facility_id")
 private List<FacitlityRole> roles = new ArrayList<>();

 public String getDescription() {
	return description;
 }

 public void setDescription(String description) {
	this.description = description;
 }

 public Long getSquareFootage() {
	return squareFootage;
 }

 public void setSquareFootage(Long squareFootage) {
	this.squareFootage = squareFootage;
 }

 public Facility getPartOf() {
	return partOf;
 }

 public void setPartOf(Facility partOf) {
	this.partOf = partOf;
 }

 public List<Facility> getContains() {
	return contains;
 }

 public void setContains(List<Facility> contains) {
	this.contains = contains;
 }

 public FacilityType getType() {
	return type;
 }

 public void setType(FacilityType type) {
	this.type = type;
 }

 public List<FacitlityRole> getRoles() {
	return roles;
 }

 public void setRoles(List<FacitlityRole> roles) {
	this.roles = roles;
 }
}
