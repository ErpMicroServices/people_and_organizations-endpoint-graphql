package org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.AbstractType;

import javax.persistence.Entity;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
public class CommunicationEventStatusType extends AbstractType<CommunicationEventStatusType> {
}
