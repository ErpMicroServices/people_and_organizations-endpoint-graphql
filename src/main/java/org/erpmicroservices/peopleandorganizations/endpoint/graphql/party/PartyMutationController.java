package org.erpmicroservices.peopleandorganizations.endpoint.graphql.party;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.classification.PartyClassification;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.classification.PartyClassificationNew;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.party.classification.PartyClassificationUpdate;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.PartyClassificationRepository;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.PartyClassificationTypeRepository;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.PartyRepository;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.PartyTypeRepository;
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

	public PartyMutationController(final PartyRepository partyRepository, final PartyTypeRepository typeRepository, final PartyClassificationRepository classificationRepository, final PartyClassificationTypeRepository classificationTypeRepository) {
		this.partyRepository = partyRepository;
		this.typeRepository = typeRepository;
		this.classificationRepository = classificationRepository;
		this.classificationTypeRepository = classificationTypeRepository;
	}

	@MutationMapping
	public Party partyCreate(@Argument final NewParty newParty) {
		final Party party = typeRepository.findById(newParty.getPartyTypeId()).stream()
				                    .map(partyType -> partyRepository.save(Party.builder()
						                                                           .partyType(partyType)
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
							                             party.setPartyType(partyType);
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
								                                                                                  .party(party)
								                                                                                  .fromDate(partyClassificationNew.getFromDate())
								                                                                                  .thruDate(partyClassificationNew.getThruDate())
								                                                                                  .value(partyClassificationNew.getValue())
								                                                                                  .partyClassificationType(classificationType)
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
					       partyClassification.setPartyClassificationType(classificationTypeRepository.findById(partyClassificationUpdate.getPartyClassificationTypeId()).orElseThrow());
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
}
