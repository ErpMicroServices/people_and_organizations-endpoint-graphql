package org.erpmicroservices.peopleandorganizations.endpoint.graphql.models;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.UUID;

@Entity(name = "communication_event_work_effort")
public class CommunicationEventWorkEffort extends AbstractPersistable<UUID> {

 @Column(name = "work_effort_id")
 private UUID workEffortId;

 public UUID getWorkEffortId() {
	return workEffortId;
 }

 public void setWorkEffortId(UUID workEffortId) {
	this.workEffortId = workEffortId;
 }
}
