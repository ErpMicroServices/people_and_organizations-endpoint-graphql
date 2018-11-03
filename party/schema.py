from graphene_django import DjangoConnectionField

from .data_access import classification_types, parties_all, party_relationships, party_role_types, party_types, \
	priority_types, relationship_types
from .graphql_types.mutation_types import *
from .graphql_types.query_types import *


class Mutation(graphene.ObjectType):
	create_person = CreatePerson.Field()
	create_organization = CreateOrganization.Field()
	add_contact_mechanism = AddContactMechanismToParty.Field()


class Query(graphene.ObjectType):
	classification_types = DjangoConnectionField(ClassificationTypeType)
	contact_mechanism_types = DjangoConnectionField(ContactMechanismTypeType)
	node = relay.Node.Field()
	parties = DjangoConnectionField(PartyModelType)
	party_roles = DjangoConnectionField(PartyRoleType)
	party_relationships = DjangoConnectionField(PartyRelationshipType)
	party_types = DjangoConnectionField(PartyTypeType)
	priority_types = DjangoConnectionField(PriorityTypeType)
	relationship_status_types = DjangoConnectionField(RelationshipStatusTypeType)
	role_types = DjangoConnectionField(RoleTypeType)
	relationship_types = DjangoConnectionField(RelationshipTypeType)

	def resolve_classification_types(self):
		return classification_types()

	def resolve_parties(self):
		return parties_all()

	def resolve_party_types(self):
		return party_types()

	def resolve_priority_types(self):
		return priority_types()

	def resolve_relationships(self):
		return party_relationships()

	def resolve_relationship_types(self):
		return relationship_types()

	def resolve_party_role_types(self):
		return party_role_types()


schema = graphene.Schema(mutation=Mutation,
												 query=Query,
												 types=[ClassificationTypeType, PartyModelType,
																PartyRelationshipType, PartyTypeType, PriorityTypeType,
																RelationshipStatusTypeType])
