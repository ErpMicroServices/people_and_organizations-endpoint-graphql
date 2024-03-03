package org.erpmicroservices.peopleandorganizations.endpoint.graphql.facility.models;

import lombok.Builder;
import lombok.Data;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.Party;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class FacilityRole {
    private UUID id;
    private FacilityRoleType facilityRoleType;
    private Party party;
    private LocalDate from;
    private LocalDate thru;
}
