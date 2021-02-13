package org.erpmicroservices.peopleandorganizations.endpoint.graphql.models;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity(name = "valid_contact_mechanism_role")
public class ValidContactMechanismRole extends AbstractPersistable<UUID> {

 @ManyToOne
 @JoinColumn(name = "contact_mechanism_type_id")
 private ContactMechanismType contactMechanismType;

 @ManyToOne
 @JoinColumn(name = "communication_event_role_type_id")
 private CommunicationEventRoleType communicationEventRoleType;

}
