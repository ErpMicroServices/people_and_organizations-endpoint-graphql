package org.erpmicroservices.peopleandorganizations.endpoint.graphql.contactmechanism.controllers;

import org.erpmicroservices.peopleandorganizations.backend.entities.ValidContactMechanismRoleEntity;
import org.erpmicroservices.peopleandorganizations.backend.repositories.CommunicationEventRoleTypeRepository;
import org.erpmicroservices.peopleandorganizations.backend.repositories.ContactMechanismTypeRepository;
import org.erpmicroservices.peopleandorganizations.backend.repositories.ValidContactMechanismRoleRepository;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.communicationevent.models.CommunicationEventRoleType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.contactmechanism.models.ContactMechanismType;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.contactmechanism.models.ValidContactMechanismRole;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.PageInfo;
import org.springframework.data.domain.Page;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

import static org.erpmicroservices.peopleandorganizations.endpoint.graphql.misc.Utils.pageInfoToPageable;

@Controller
public class ValidContactMechanismRoleController {

    private final ValidContactMechanismRoleRepository validContactMechanismRoleRepository;
    private final ContactMechanismTypeRepository contactMechanismTypeRepository;
    private final CommunicationEventRoleTypeRepository communicationEventRoleTypeRepository;

    public ValidContactMechanismRoleController(ValidContactMechanismRoleRepository validContactMechanismRoleRepository,
                                              ContactMechanismTypeRepository contactMechanismTypeRepository,
                                              CommunicationEventRoleTypeRepository communicationEventRoleTypeRepository) {
        this.validContactMechanismRoleRepository = validContactMechanismRoleRepository;
        this.contactMechanismTypeRepository = contactMechanismTypeRepository;
        this.communicationEventRoleTypeRepository = communicationEventRoleTypeRepository;
    }

    @QueryMapping
    public List<ValidContactMechanismRole> validContactMechanismRoles(@Argument PageInfo pageInfo) {
        Page<ValidContactMechanismRoleEntity> roles = validContactMechanismRoleRepository.findAll(pageInfoToPageable(pageInfo));

        return roles.stream()
                .map(entity -> {
                    ContactMechanismType contactMechanismType = contactMechanismTypeRepository.findById(entity.getContactMechanismTypeId())
                            .map(type -> ContactMechanismType.builder()
                                    .id(type.getId())
                                    .description(type.getDescription())
                                    .build())
                            .orElse(null);

                    CommunicationEventRoleType communicationEventRoleType = communicationEventRoleTypeRepository.findById(entity.getCommunicationEventRoleTypeId())
                            .map(type -> CommunicationEventRoleType.builder()
                                    .id(type.getId())
                                    .description(type.getDescription())
                                    .build())
                            .orElse(null);

                    return ValidContactMechanismRole.builder()
                            .id(entity.getId())
                            .contactMechanismType(contactMechanismType)
                            .communicationEventRoleType(communicationEventRoleType)
                            .build();
                })
                .collect(Collectors.toList());
    }
}
