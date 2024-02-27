package org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.graphql;

import graphql.relay.Edge;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.model.CommunicationEvent;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.model.NewCommunicationEvent;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.repositories.CommunicationEventRepository;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.repositories.CommunicationEventStatusTypeRepository;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.repositories.CommunicationEventTypeRepository;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.Cursor;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.PageInfo;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.models.*;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.kase.repositories.*;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.lang.String.valueOf;
import static org.erpmicroservices.peopleandorganizations.endpoint.graphql.misc.Utils.pageInfoToPageable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewCaseRole {
	private UUID caseId;
	private UUID caseRoleTypeId;
	private UUID partyId;
	private LocalDate fromDate;


}
