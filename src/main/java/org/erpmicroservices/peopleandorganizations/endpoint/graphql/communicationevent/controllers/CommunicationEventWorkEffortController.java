package org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.controllers;

import org.erpmicroservices.peopleandorganizations.backend.entities.CommunicationEventEntity;
import org.erpmicroservices.peopleandorganizations.backend.entities.CommunicationEventWorkEffortEntity;
import org.erpmicroservices.peopleandorganizations.backend.repositories.CommunicationEventRepository;
import org.erpmicroservices.peopleandorganizations.backend.repositories.CommunicationEventWorkEffortRepository;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.models.CommunicationEvent;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.models.CommunicationEventWorkEffort;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

import static org.erpmicroservices.peopleandorganizations.endpoint.graphql.misc.Utils.pageInfoToPageable;

@Controller
public class CommunicationEventWorkEffortController {

    private final CommunicationEventWorkEffortRepository communicationEventWorkEffortRepository;
    private final CommunicationEventRepository communicationEventRepository;

    public CommunicationEventWorkEffortController(CommunicationEventWorkEffortRepository communicationEventWorkEffortRepository,
                                                CommunicationEventRepository communicationEventRepository) {
        this.communicationEventWorkEffortRepository = communicationEventWorkEffortRepository;
        this.communicationEventRepository = communicationEventRepository;
    }

    @SchemaMapping(typeName = "CommunicationEvent", field = "workEfforts")
    public List<CommunicationEventWorkEffort> workEfforts(@Argument PageInfo pageInfo, CommunicationEvent communicationEvent) {
        Page<CommunicationEventWorkEffortEntity> workEfforts = communicationEventWorkEffortRepository.findByCommunicationEventId(
                communicationEvent.getId(), pageInfoToPageable(pageInfo));

        return workEfforts.stream()
                .map(entity -> CommunicationEventWorkEffort.builder()
                        .id(entity.getId())
                        .communicationEvent(communicationEvent)
                        .workEffortId(entity.getWorkEffortId())
                        .build())
                .collect(Collectors.toList());
    }
}
