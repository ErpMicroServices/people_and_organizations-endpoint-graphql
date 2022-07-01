package org.erpmicroservices.peopleandorganizations.endpoint.graphql.controllers;

import graphql.relay.Edge;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.*;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.Party;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.PartyClassification;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.PartyContactMechanism;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.PartyId;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.PartyClassificationRepository;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.PartyContactMechanismRepository;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.PartyIdRepository;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.PartyRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.valueOf;
import static org.erpmicroservices.peopleandorganizations.endpoint.graphql.misc.Utils.pageInfoToPageable;


@Controller
public class PartyController {

	private final PartyRepository repository;
	private final PartyClassificationRepository classificationRepository;

	private final PartyContactMechanismRepository contactMechanismRepository;

	private final PartyIdRepository idRepository;

	public PartyController(final PartyRepository repository, final PartyClassificationRepository classificationRepository, final PartyContactMechanismRepository contactMechanismRepository, final PartyIdRepository idRepository) {
		this.repository = repository;
		this.classificationRepository = classificationRepository;
		this.contactMechanismRepository = contactMechanismRepository;
		this.idRepository = idRepository;
	}

	@QueryMapping
	public PartyConnection parties(@Argument PageInfo pageInfo) {
		final List<Edge<Party>> edges = repository.findAll(pageInfoToPageable(pageInfo)).stream()
				                                .map(node -> PartyEdge.builder()
						                                             .node(node)
						                                             .cursor(Cursor.builder().value(valueOf(node.getId().hashCode())).build())
						                                             .build())
				                                .collect(Collectors.toList());
		return PartyConnection.builder()
				       .edges(edges)
				       .pageInfo(pageInfo)
				       .build();
	}

	@SchemaMapping
	public PartyClassificationConnection classifications(@Argument PageInfo pageInfo, Party party) {
		final List<Edge<PartyClassification>> edges = classificationRepository.findPartyClassificationsByParty(party, pageInfoToPageable(pageInfo)).stream()
				                                              .map(partyClassification -> PartyClassificationEdge.builder()
						                                                                          .node(partyClassification)
						                                                                          .cursor(Cursor.builder().value(valueOf(partyClassification.getId().hashCode())).build())
						                                                                          .build())
				                                              .collect(Collectors.toList());
		return PartyClassificationConnection.builder()
				       .edges(edges)
				       .pageInfo(pageInfo)
				       .build();
	}

	@SchemaMapping
	public PartyContactMechanismConnection contactMechanisms(@Argument PageInfo pageInfo, Party party) {
		final List<Edge<PartyContactMechanism>> edges = contactMechanismRepository.findPartyContactMechanismByParty(party, pageInfoToPageable(pageInfo)).stream()
				                                                .map(node -> PartyContactMechanismEdge.builder()
						                                                             .node(node)
						                                                             .cursor(Cursor.builder().value(valueOf(node.getId().hashCode())).build())
						                                                             .build())
				                                                .collect(Collectors.toList());
		return PartyContactMechanismConnection.builder()
				       .edges(edges)
				       .pageInfo(pageInfo)
				       .build();
	}

	@SchemaMapping
	public PartyIdConnection iDs(@Argument PageInfo pageInfo, Party party) {
		final List<Edge<PartyId>> edges = idRepository.findPartyIdsByPartyEquals(party, pageInfoToPageable(pageInfo)).stream()
				                                  .map(node -> PartyIdEdge.builder()
						                                               .node(node)
						                                               .cursor(Cursor.builder().value(valueOf(node.getId().hashCode())).build())
						                                               .build())
				                                  .collect(Collectors.toList());
		return PartyIdConnection.builder()
				       .edges(edges)
				       .pageInfo(pageInfo)
				       .build();
	}
}
