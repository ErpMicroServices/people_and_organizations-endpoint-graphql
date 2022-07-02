package org.erpmicroservices.peopleandorganizations.endpoint.graphql.controllers;

import graphql.relay.Edge;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.*;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.models.*;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.*;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.valueOf;
import static org.erpmicroservices.peopleandorganizations.endpoint.graphql.misc.Utils.pageInfoToPageable;


@Controller
public class PartyQueryController {

	private final PartyRepository repository;
	private final PartyClassificationRepository classificationRepository;

	private final PartyContactMechanismRepository contactMechanismRepository;

	private final PartyIdRepository idRepository;

	private final PartyNameRepository partyNameRepository;

	private final PartyRelationshipRepository relationshipRepository;

	private final PartyRoleRepository roleRepository;

	public PartyQueryController(final PartyRepository repository, final PartyClassificationRepository classificationRepository, final PartyContactMechanismRepository contactMechanismRepository, final PartyIdRepository idRepository, final PartyNameRepository partyNameRepository, final PartyRelationshipRepository relationshipRepository, final PartyRoleRepository roleRepository) {
		this.repository = repository;
		this.classificationRepository = classificationRepository;
		this.contactMechanismRepository = contactMechanismRepository;
		this.idRepository = idRepository;
		this.partyNameRepository = partyNameRepository;
		this.relationshipRepository = relationshipRepository;
		this.roleRepository = roleRepository;
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

	@SchemaMapping
	public PartyNameConnection names(@Argument PageInfo pageInfo, Party party) {
		final List<Edge<PartyName>> edges = partyNameRepository.findPartyNamesByParty(party, pageInfoToPageable(pageInfo)).stream()
				                                    .map(node -> PartyNameEdge.builder()
						                                                 .node(node)
						                                                 .cursor(Cursor.builder().value(valueOf(node.getId().hashCode())).build())
						                                                 .build())
				                                    .collect(Collectors.toList());
		return PartyNameConnection.builder()
				       .edges(edges)
				       .pageInfo(pageInfo)
				       .build();
	}

	@SchemaMapping
	public PartyRelationshipConnection relationships(@Argument PageInfo pageInfo, Party party) {
		final List<Edge<PartyRelationship>> edges = relationshipRepository.findPartyRelationshipByFromPartyRole_PartyOrToPartyRole_Party(party, party, pageInfoToPageable(pageInfo)).stream()
				                                            .map(node -> PartyRelationshipEdge.builder()
						                                                         .node(node)
						                                                         .cursor(Cursor.builder().value(valueOf(node.getId().hashCode())).build())
						                                                         .build())
				                                            .collect(Collectors.toList());
		return PartyRelationshipConnection.builder()
				       .edges(edges)
				       .pageInfo(pageInfo)
				       .build();
	}

	@SchemaMapping
	public PartyRoleConnection roles(@Argument PageInfo pageInfo, Party party) {
		final List<Edge<PartyRole>> edges = roleRepository.findPartyRolesByParty(party, pageInfoToPageable(pageInfo)).stream()
				                                    .map(node -> PartyRoleEdge.builder()
						                                                 .node(node)
						                                                 .cursor(Cursor.builder().value(valueOf(node.getId().hashCode())).build())
						                                                 .build())
				                                    .collect(Collectors.toList());
		return PartyRoleConnection.builder()
				       .edges(edges)
				       .pageInfo(pageInfo)
				       .build();
	}
}
