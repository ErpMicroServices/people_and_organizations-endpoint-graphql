import graphene
from graphene import relay
from graphene_django import DjangoObjectType

from party.data_access import find_classification_type_by_id, find_party_by_id
from party.models.party import *
from party.models.party import ContactMechanism as ContactMechanismDataModel, \
	PartyToContactMechanism as PartyToContactMechanismDataModel


class ClassificationTypeType(DjangoObjectType):
	class Meta:
		model = ClassificationType
		interfaces = (relay.Node,)

	@classmethod
	def get_node(cls, id):
		node = find_classification_type_by_id(id)
		return node


class ContactMechanism(DjangoObjectType):
	class Meta:
		model = ContactMechanismDataModel
		interfaces = (relay.Node,)

	@classmethod
	def get_node(cls, id):
		return ContactMechanismDataModel.objects.get(id=id)


class ContactMechanismTypeType(DjangoObjectType):
	class Meta:
		model = ContactMechanismType
		interfaces = (relay.Node,)

	@classmethod
	def get_node(cls, id):
		node = find_contact_mechanism_type_by_id(id)
		return node


class PartyModelType(DjangoObjectType):
	class Meta:
		model = Party
		interfaces = (relay.Node,)

	@classmethod
	def get_node(cls, id):
		node = find_party_by_id(id)
		return node


class PartyContactMechanism(DjangoObjectType):
	class Meta:
		model = PartyToContactMechanismDataModel
		interfaces = (relay.Node,)

	@classmethod
	def get_node(cls, id):
		node = PartyToContactMechanism.objects.get(id=id)
		return node


class PartyRoleType(DjangoObjectType):
	class Meta:
		model = PartyRole
		interfaces = (relay.Node,)

	@classmethod
	def get_node(cls, id):
		node = PartyRole.objects.get(id=id)
		return node


class PartyRelationshipType(DjangoObjectType):
	from_party = graphene.Field(PartyRoleType)

	class Meta:
		model = PartyRelationship
		interfaces = (relay.Node,)

	@classmethod
	def get_node(cls, id):
		node = PartyRelationship.objects.get(id=id)
		return node


class PartyTypeType(DjangoObjectType):
	class Meta:
		model = PartyType
		interfaces = (relay.Node,)

	@classmethod
	def get_node(cls, id):
		node = PartyType.objects.get(pk=id)
		return node


class PriorityTypeType(DjangoObjectType):
	class Meta:
		model = PriorityType
		interfaces = (relay.Node,)

	@classmethod
	def get_node(cls, id):
		node = PriorityType.objects.get(id=id)
		return node


class RelationshipStatusTypeType(DjangoObjectType):
	class Meta:
		model = RelationshipStatusType
		interfaces = (relay.Node,)

	@classmethod
	def get_node(cls, id):
		node = RelationshipStatusType.objects.get(id=id)
		return node


class RoleTypeType(DjangoObjectType):
	class Meta:
		model = RoleType
		interfaces = (relay.Node,)

	@classmethod
	def get_node(cls, id):
		node = RoleType.objects.get(id=id)
		return node


class RelationshipTypeType(DjangoObjectType):
	class Meta:
		model = RelationshipType
		interfaces = (relay.Node,)

	@classmethod
	def get_node(cls, id):
		node = RelationshipType.objects.get(id=id)
		return node
