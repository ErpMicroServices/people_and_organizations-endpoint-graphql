import graphene
import pendulum
from graphene import relay

from party.data_access import add_contact_mechanism_to_party, create_organization, create_person
from .query_types import PartyContactMechanism, PartyModelType


class CreateOrganization(relay.ClientIDMutation):
	class Input:
		government_id = graphene.String()
		name = graphene.String()
		nickname = graphene.String()
		comment = graphene.String()

	organization = graphene.Field(PartyModelType)

	@classmethod
	def mutate_and_get_payload(cls, root, info, government_id=None, name=None, nickname=None, comment=None,
														 client_mutation_id=None):
		organization = create_organization(government_id, name, nickname, comment)
		return CreateOrganization(organization)


class CreatePerson(relay.ClientIDMutation):
	class Input:
		government_id = graphene.String()
		first_name = graphene.String()
		last_name = graphene.String()
		title = graphene.String()
		nickname = graphene.String()
		date_of_birth = graphene.String()
		comment = graphene.String()

	person = graphene.Field(PartyModelType)

	@classmethod
	def mutate_and_get_payload(cls, root, info, government_id=None, first_name=None, last_name=None, title=None,
														 nickname=None, date_of_birth=None, comment=None, client_mutation_id=None):
		person = create_person(government_id, first_name, last_name, title, nickname, date_of_birth, comment)
		return CreatePerson(person)


class AddContactMechanismToParty(relay.ClientIDMutation):
	class Input:
		party_id = graphene.String()
		from_date = graphene.String()
		thru_date = graphene.String()
		comment = graphene.String()
		end_point = graphene.String()
		directions = graphene.String()
		contact_mechanism_type_id = graphene.String()
		geographic_boundary_id = graphene.String()

	party_contact_mechanism = graphene.Field(PartyContactMechanism)

	@classmethod
	def mutate_and_get_payload(cls, root, info, party_id: str, end_point: str, contact_mechanism_type_id: str,
														 from_date: str = None, thru_date: str = None,
														 comment: str = None, directions: str = None,
														 geographic_boundary_id: str = None):
		return AddContactMechanismToParty(add_contact_mechanism_to_party(party_id=party_id, end_point=end_point,
																																		 contact_mechanism_type_id=contact_mechanism_type_id,
																																		 from_date=pendulum.parse(from_date),
																																		 thru_date=(None if (
																																				thru_date is None) else pendulum.parse(
																																			 thru_date)),
																																		 comment=comment, directions=directions,
																																		 geographic_boundary_id=geographic_boundary_id))
