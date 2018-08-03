import graphene
from graphene import Schema, relay, resolve_only_args
from graphene_django import DjangoConnectionField, DjangoObjectType

from .models import *


class ClassificationTypeType(DjangoObjectType):
    class Meta:
        model = ClassificationType
        interfaces = (relay.Node,)

    @classmethod
    def get_node(cls, id):
        node = ClassificationType.objects.get(id=id)
        return node


class PartyModelType(DjangoObjectType):
    class Meta:
        model = Party
        interfaces = (relay.Node,)

    @classmethod
    def get_node(cls, id):
        node = Party.objects.get(id=id)
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
        node = PartyType.objects.get(id=id)
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


class Query(graphene.ObjectType):
    classification_types = graphene.Field(ClassificationTypeType)
    node = relay.Node.Field()
    parties = DjangoConnectionField(PartyModelType)
    party_types = graphene.Field(PartyTypeType)
    priority_types = graphene.Field(PriorityTypeType)
    relationship_types = graphene.Field(RelationshipStatusTypeType)
    relationships = DjangoConnectionField(PartyRelationshipType)

    @resolve_only_args
    def resolve_classification_types(self):
        return ClassificationType.objects.all()

    @resolve_only_args
    def resolve_parties(self):
        return Party.objects.all()

    @resolve_only_args
    def resolve_party_types(self):
        return PartyType.objects.all()

    @resolve_only_args
    def resolve_priority_types(self):
        return PriorityType.objects.all()

    @resolve_only_args
    def resolve_relationships(self):
        return PartyRelationship.objects.all()

    @resolve_only_args
    def resolve_relationship_types(self):
        return RelationshipStatusType.objects.all()

    @resolve_only_args
    def resolve_party_role_types(self):
        return PartyRole.objects.all()

schema = graphene.Schema(query=Query, types=[ClassificationTypeType, PartyModelType,
                                             PartyRelationshipType, PartyTypeType, PriorityTypeType, RelationshipStatusTypeType])
