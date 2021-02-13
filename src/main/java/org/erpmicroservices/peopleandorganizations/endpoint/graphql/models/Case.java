package org.erpmicroservices.peopleandorganizations.endpoint.graphql.models;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "kase")
public class Case extends AbstractPersistable<UUID> {
 private String description;

 private LocalDateTime startedAt;

 @ManyToOne
 @JoinColumn(name = "case_type_id")
 private CaseType type;

 @ManyToOne
 @JoinColumn(name = "case_status_type_id")
 private CaseStatusType caseStatus;

 public String getDescription() {
  return description;
 }

 public void setDescription(String description) {
  this.description = description;
 }

 public LocalDateTime getStartedAt() {
  return startedAt;
 }

 public void setStartedAt(LocalDateTime startedAt) {
  this.startedAt = startedAt;
 }

 public CaseType getType() {
  return type;
 }

 public void setType(CaseType type) {
  this.type = type;
 }

 public CaseStatusType getCaseStatus() {
  return caseStatus;
 }

 public void setCaseStatus(CaseStatusType caseStatus) {
  this.caseStatus = caseStatus;
 }
}
