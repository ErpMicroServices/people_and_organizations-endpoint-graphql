package org.erpmicroservices.peopleandorganizations.endpoint.graphql.models;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "geographic_boundary")
public class GeographicBoundary extends AbstractPersistable<UUID> {

 private String geoCode;

 private String name;

 private String abbreviation;

 @ManyToOne
 @JoinColumn(name = "geographic_boundary_type_id")
 private GeographicBoundaryType type;

 @ManyToMany
 @JoinTable(
	 name = "geographic_boundary_association",
	 joinColumns = @JoinColumn(name = "in_boundary"),
	 inverseJoinColumns = @JoinColumn(name = "within_boundary"))
 private List<GeographicBoundary> inside = new ArrayList<>();

 @ManyToMany
 @JoinTable(
	 name = "geographic_boundary_association",
	 joinColumns = @JoinColumn(name = "within_boundary"),
	 inverseJoinColumns = @JoinColumn(name = "in_boundary"))
 private List<GeographicBoundary> within = new ArrayList<>();

 public String getGeoCode() {
	return geoCode;
 }

 public void setGeoCode(String geoCode) {
	this.geoCode = geoCode;
 }

 public String getName() {
	return name;
 }

 public void setName(String name) {
	this.name = name;
 }

 public String getAbbreviation() {
	return abbreviation;
 }

 public void setAbbreviation(String abbreviation) {
	this.abbreviation = abbreviation;
 }

 public GeographicBoundaryType getType() {
	return type;
 }

 public void setType(GeographicBoundaryType type) {
	this.type = type;
 }

 public List<GeographicBoundary> getInside() {
	return inside;
 }

 public void setInside(List<GeographicBoundary> inside) {
	this.inside = inside;
 }

 public List<GeographicBoundary> getWithin() {
	return within;
 }

 public void setWithin(List<GeographicBoundary> within) {
	this.within = within;
 }
}

