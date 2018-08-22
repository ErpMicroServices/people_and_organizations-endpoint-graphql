import graphene
from graphene import relay

from party.data_access import create_organization, create_person
from .query_types import PartyModelType


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
