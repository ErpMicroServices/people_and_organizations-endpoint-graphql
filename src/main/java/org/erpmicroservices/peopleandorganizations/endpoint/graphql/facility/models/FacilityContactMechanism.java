package org.erpmicroservices.peopleandorganizations.endpoint.graphql.facility.models;

import lombok.Builder;
import lombok.Data;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.contactmechanism.models.ContactMechanism;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class FacilityContactMechanism {
    private UUID id;
    private ContactMechanism contactMechanism;
    private LocalDate fromDate;
    private LocalDate thruData;
}
