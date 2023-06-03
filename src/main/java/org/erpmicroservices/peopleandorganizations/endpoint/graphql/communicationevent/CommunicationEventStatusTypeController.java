package org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.PageInfo;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.CommunicationEventStatusTypeRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CommunicationEventStatusTypeController {

	private final CommunicationEventStatusTypeRepository communicationEventStatusTypeRepository;

	public CommunicationEventStatusTypeController(CommunicationEventStatusTypeRepository communicationEventStatusTypeRepository) {
		this.communicationEventStatusTypeRepository = communicationEventStatusTypeRepository;
	}

	@QueryMapping
	public List<CommunicationEventStatusType> communicationEventStatusTypes(@Argument PageInfo pageInfo) {
		return communicationEventStatusTypeRepository.findAll();
	}
}
