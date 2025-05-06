package org.erpmicroservices.peopleandorganizations.endpoint.graphql.party;

import graphql.relay.Edge;
import org.erpmicroservices.peopleandorganizations.backend.entities.*;
import org.erpmicroservices.peopleandorganizations.backend.repositories.*;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.Cursor;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.dto.PageInfo;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.classification.PartyClassificationConnection;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.classification.PartyClassificationEdge;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.contactmechanism.PartyContactMechanismConnection;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.contactmechanism.PartyContactMechanismEdge;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.name.PartyNameConnection;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.name.PartyNameEdge;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.relationship.PartyRelationshipConnection;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.relationship.PartyRelationshipEdge;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.role.PartyRoleConnection;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.role.PartyRoleEdge;
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
		final List<Edge<PartyEntity>> edges = repository.findAll(pageInfoToPageable(pageInfo)).stream()
				                                .map(node -> PartyEdgeEntity.builder()
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
	public PartyClassificationConnection classifications(@Argument PageInfo pageInfo, PartyEntity partyEntity) {
		final List<Edge<PartyClassificationEntity>> edges = classificationRepository.findPartyClassificationsByPartyId(partyEntity.getId(), pageInfoToPageable(pageInfo)).stream()
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
	public PartyContactMechanismConnection contactMechanisms(@Argument PageInfo pageInfo, PartyEntity partyEntity) {
		final List<Edge<PartyContactMechanismEntity>> edges = contactMechanismRepository.findPartyContactMechanismByPartyId(partyEntity.getId(), pageInfoToPageable(pageInfo)).stream()
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
	public PartyIdConnection iDs(@Argument PageInfo pageInfo, PartyEntity partyEntity) {
		final List<Edge<PartyIdEntity>> edges = idRepository.findPartyIdsByPartyId(partyEntity.getId(), pageInfoToPageable(pageInfo)).stream()
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
	public PartyNameConnection names(@Argument PageInfo pageInfo, PartyEntity partyEntity) {
		final List<Edge<PartyNameEntity>> edges = partyNameRepository.findPartyNamesByPartyId(partyEntity.getId(), pageInfoToPageable(pageInfo)).stream()
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
	public PartyRelationshipConnection relationships(@Argument PageInfo pageInfo, PartyEntity partyEntity) {
		// Get all party roles for the given party
		List<PartyRoleEntity> partyRoles = roleRepository.findPartyRolesByPartyId(partyEntity.getId(), pageInfoToPageable(pageInfo)).getContent();

		// For each party role, get all party relationships where the party role is either the "from" party role or the "to" party role
		List<Edge<PartyRelationshipEntity>> edges = partyRoles.stream()
				.flatMap(partyRole -> {
					// Get relationships where this party role is either the "from" or "to" party role
					return relationshipRepository.findPartyRelationshipByFromPartyRoleIdOrToPartyRoleId(
							partyRole.getId(), partyRole.getId(), pageInfoToPageable(pageInfo)).stream();
				})
				.distinct() // Remove duplicates
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
	public PartyRoleConnection roles(@Argument PageInfo pageInfo, PartyEntity partyEntity) {
		final List<Edge<PartyRoleEntity>> edges = roleRepository.findPartyRolesByPartyId(partyEntity.getId(), pageInfoToPageable(pageInfo)).stream()
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
