import graphene
from graphene import relay, resolve_only_args
from graphene_django import DjangoConnectionField, DjangoObjectType

from .data_access import create_organization, create_person, find_classification_type_by_id, find_party_by_id
from .models.party import *


class ClassificationTypeType(DjangoObjectType):
    class Meta:
        model = ClassificationType
        interfaces = (relay.Node,)

    @classmethod
    def get_node(cls, id):
        node = find_classification_type_by_id(id)
        return node


class PartyModelType(DjangoObjectType):
    class Meta:
        model = Party
        interfaces = (relay.Node,)

    @classmethod
    def get_node(cls, id):
        node = find_party_by_id(id)
        return node


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
        print("mutate_and_get_payload start")
        person = create_person(government_id, first_name, last_name, title, nickname, date_of_birth, comment)
        print("mutate_and_get_payload end")
        return CreatePerson(person)


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


class Mutation(graphene.ObjectType):
    create_person = CreatePerson.Field()
    create_organization = CreateOrganization.Field()


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


schema = graphene.Schema(mutation=Mutation,
                         query=Query,
                         types=[ClassificationTypeType, PartyModelType,
                                PartyRelationshipType, PartyTypeType, PriorityTypeType,
                                RelationshipStatusTypeType])
