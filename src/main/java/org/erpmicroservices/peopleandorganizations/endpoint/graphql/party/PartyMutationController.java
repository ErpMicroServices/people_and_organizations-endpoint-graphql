package org.erpmicroservices.peopleandorganizations.endpoint.graphql.party;

import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.PartyRepository;
import org.erpmicroservices.peopleandorganizations.endpoint.graphql.repositories.PartyTypeRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;


@Controller
public class PartyMutationController {

	private final PartyRepository repository;

	private final PartyTypeRepository typeRepository;

	public PartyMutationController(final PartyRepository repository, final PartyTypeRepository typeRepository) {
		this.repository = repository;
		this.typeRepository = typeRepository;
	}

	@MutationMapping
	public Party createParty(@Argument final NewParty newParty) {
		final Party party = typeRepository.findById(newParty.getPartyTypeId()).stream()
				                    .map(partyType -> repository.save(Party.builder()
						                                                      .partyType(partyType)
						                                                      .comment(newParty.getComment())
						                                                      .build()))
				                    .findFirst()
				                    .orElseThrow();
		return party;
	}

}
