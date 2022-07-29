package org.erpmicroservices.peopleandorganizations.endpoint.graphql.party;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.contactmechanism.ContactMechanism;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.classification.PartyClassification;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.classification.PartyClassificationNew;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.classification.PartyClassificationUpdate;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.contactmechanism.PartyContactMechanism;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.contactmechanism.PartyContactMechanismNew;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.contactmechanism.PartyContactMechanismPurposeRepository;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.*;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
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
	public Party partyCreate(@Argument final NewParty newParty) {
		final Party party = typeRepository.findById(newParty.getPartyTypeId()).stream()
				                    .map(partyType -> partyRepository.save(Party.builder()
						                                                           .partyTypeId(newParty.getPartyTypeId())
						                                                           .comment(newParty.getComment())
						                                                           .build()))
				                    .findFirst()
				                    .orElseThrow();
		return party;
	}

	@MutationMapping
	public Party partyUpdate(@Argument final UpdateParty updateParty) {
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
	public PartyClassification addClassification(@Argument PartyClassificationNew partyClassificationNew) {
		return partyRepository.findById(partyClassificationNew.getPartyId()).stream()
				       .flatMap(party -> classificationTypeRepository.findById(partyClassificationNew.getPartyClassificationTypeId()).stream()
						                         .map(classificationType -> classificationRepository.save(PartyClassification.builder()
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
	public PartyClassification updateClassification(@Argument PartyClassificationUpdate partyClassificationUpdate) {
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
	public PartyClassification removeClassification(@Argument UUID id) {
		return classificationRepository.findById(id).stream()
				       .map(classification -> {
					       classificationRepository.deleteById(id);
					       return classification;
				       })
				       .findFirst()
				       .orElseThrow();
	}

	@MutationMapping(name = "partyContactMechanismAdd")
	public PartyContactMechanism addContactMechanism(@Argument PartyContactMechanismNew partyContactMechanismNew) {
		final ContactMechanism contactMechanism = contactMechanismRepository.save(
				ContactMechanism.builder()
						.contactMechanismTypeId(partyContactMechanismNew.getContactMechanism().getContactMechanismTypeId())
						.directions(partyContactMechanismNew.getContactMechanism().getDirections())
						.endPoint(partyContactMechanismNew.getContactMechanism().getEndPoint())
						.build()
		);
		return partyContactMechanismRepository.save( PartyContactMechanism.builder()
				                                             .comment(partyContactMechanismNew.getComment())
				                                             .contactMechanismId(contactMechanism.getId())
				                                             .doNotSolicitIndicator(partyContactMechanismNew.isDoNotSolicitIndicator())
				                                             .partyId(partyContactMechanismNew.getPartyId())
				                                             .fromDate(partyContactMechanismNew.getFromDate())
				                                             .thruDate(partyContactMechanismNew.getThruDate())
				                                             .build());
	}
}
