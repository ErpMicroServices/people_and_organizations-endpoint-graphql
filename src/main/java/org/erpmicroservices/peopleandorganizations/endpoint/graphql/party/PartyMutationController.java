package org.erpmicroservices.peopleandorganizations.endpoint.graphql.party;

import org.erpmicroservices.peopleandorganizations.backend.entities.*;
import org.erpmicroservices.peopleandorganizations.backend.repositories.*;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.classification.PartyClassificationNew;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.classification.PartyClassificationUpdate;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.contactmechanism.PartyContactMechanismNew;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.UUID;


@Controller
public class PartyMutationController {

	private final PartyRepository partyRepository;

	private final PartyTypeRepository typeRepository;

	private final PartyClassificationRepository classificationRepository;

	private final PartyClassificationTypeRepository classificationTypeRepository;

	private final PartyContactMechanismRepository partyContactMechanismRepository;

	private final ContactMechanismRepository contactMechanismRepository;

	private final ContactMechanismTypeRepository contactMechanismTypeRepository;

	private final GeographicBoundaryRepository geographicBoundaryRepository;

	private final PartyContactMechanismPurposeTypeRepository partyContactMechanismPurposeTypeRepository;

	private final PartyContactMechanismPurposeRepository partyContactMechanismPurposeRepository;

	public PartyMutationController(final PartyRepository partyRepository, final PartyTypeRepository typeRepository, final PartyClassificationRepository classificationRepository, final PartyClassificationTypeRepository classificationTypeRepository, final PartyContactMechanismRepository partyContactMechanismRepository, final ContactMechanismRepository contactMechanismRepository, final ContactMechanismTypeRepository contactMechanismTypeRepository, final GeographicBoundaryRepository geographicBoundaryRepository, final PartyContactMechanismPurposeTypeRepository partyContactMechanismPurposeTypeRepository, final PartyContactMechanismPurposeRepository partyContactMechanismPurposeRepository) {
		this.partyRepository = partyRepository;
		this.typeRepository = typeRepository;
		this.classificationRepository = classificationRepository;
		this.classificationTypeRepository = classificationTypeRepository;
		this.partyContactMechanismRepository = partyContactMechanismRepository;
		this.contactMechanismRepository = contactMechanismRepository;
		this.contactMechanismTypeRepository = contactMechanismTypeRepository;
		this.geographicBoundaryRepository = geographicBoundaryRepository;
		this.partyContactMechanismPurposeTypeRepository = partyContactMechanismPurposeTypeRepository;
		this.partyContactMechanismPurposeRepository = partyContactMechanismPurposeRepository;
	}

	@MutationMapping
	public PartyEntity partyCreate(@Argument final NewParty newParty) {
		final PartyEntity partyEntity = typeRepository.findById(newParty.getPartyTypeId()).stream()
				                    .map(partyType -> partyRepository.save(PartyEntity.builder()
						                                                           .partyTypeId(newParty.getPartyTypeId())
						                                                           .comment(newParty.getComment())
						                                                           .build()))
				                    .findFirst()
				                    .orElseThrow();
		return partyEntity;
	}

	@MutationMapping
	public PartyEntity partyUpdate(@Argument final UpdateParty updateParty) {
		return typeRepository.findById(updateParty.getPartyTypeId()).stream()
				       .flatMap(partyType -> partyRepository.findById(updateParty.getId()).stream()
						                             .map(party -> {
							                             party.setComment(updateParty.getComment());
							                             party.setPartyTypeId(updateParty.getPartyTypeId());
							                             return partyRepository.save(party);
						                             }))
				       .findFirst()
				       .orElseThrow();

	}

	@MutationMapping(name = "partyClassificationAdd")
	public PartyClassificationEntity addClassification(@Argument PartyClassificationNew partyClassificationNew) {
		return partyRepository.findById(partyClassificationNew.getPartyId()).stream()
				       .flatMap(party -> classificationTypeRepository.findById(partyClassificationNew.getPartyClassificationTypeId()).stream()
						                         .map(classificationType -> classificationRepository.save(PartyClassificationEntity.builder()
								                                                                                  .partyId(partyClassificationNew.getPartyId())
								                                                                                  .fromDate(partyClassificationNew.getFromDate())
								                                                                                  .thruDate(partyClassificationNew.getThruDate())
								                                                                                  .value(partyClassificationNew.getValue())
								                                                                                  .partyClassificationTypeId(partyClassificationNew.getPartyClassificationTypeId())
								                                                                                  .build())))
				       .findFirst()
				       .orElseThrow();
	}

	@MutationMapping(name = "partyClassificationUpdate")
	public PartyClassificationEntity updateClassification(@Argument PartyClassificationUpdate partyClassificationUpdate) {
		return classificationRepository.findById(partyClassificationUpdate.getId()).stream()
				       .map(partyClassification -> {
					       partyClassification.setFromDate(partyClassificationUpdate.getFromDate());
					       partyClassification.setThruDate(partyClassificationUpdate.getThruDate());
					       partyClassification.setValue(partyClassificationUpdate.getValue());
					       partyClassification.setPartyClassificationTypeId(partyClassificationUpdate.getPartyClassificationTypeId());
					       return classificationRepository.save(partyClassification);
				       })
				       .findFirst()
				       .orElseThrow();

	}

	@MutationMapping(name = "partyClassificationRemove")
	public PartyClassificationEntity removeClassification(@Argument UUID id) {
		return classificationRepository.findById(id).stream()
				       .map(classification -> {
					       classificationRepository.deleteById(id);
					       return classification;
				       })
				       .findFirst()
				       .orElseThrow();
	}

	@MutationMapping(name = "partyContactMechanismAdd")
	public PartyContactMechanismEntity addContactMechanism(@Argument PartyContactMechanismNew partyContactMechanismNew) {

		return partyContactMechanismRepository.save(PartyContactMechanismEntity.builder()
				                                            .comment(partyContactMechanismNew.getComment())
				                                            .contactMechanismId(partyContactMechanismNew.getContactMechanismId())
				                                            .doNotSolicitIndicator(partyContactMechanismNew.isDoNotSolicitIndicator())
				                                            .partyId(partyContactMechanismNew.getPartyId())
				                                            .fromDate(partyContactMechanismNew.getFromDate())
				                                            .thruDate(partyContactMechanismNew.getThruDate())
				                                            .partyContactMechanismPurposeId(partyContactMechanismNew.getPurposeId())
				                                            .build());
	}

	@SchemaMapping
	public ContactMechanismEntity contactMechanism(PartyContactMechanismEntity partyContactMechanismEntity) {
		return contactMechanismRepository.findById(partyContactMechanismEntity.getContactMechanismId()).orElseThrow();
	}

	@SchemaMapping
	public PartyContactMechanismPurposeEntity purpose(PartyContactMechanismEntity partyContactMechanismEntity) {
		return partyContactMechanismPurposeRepository.findById(partyContactMechanismEntity.getPartyContactMechanismPurposeId()).orElseThrow();
	}
}
