from .models import *

party_types = PartyType.objects.all()
person_type = party_types.get(description='Person')
organization_type = party_types.get(description='Organization')


def find_classification_type_by_id(id):
    return ClassificationType.objects.get(id=id)


def find_party_by_id(id):
    return Party.objects.get(id=id)


def create_organization(government_id, name, nickname, comment):
    return Party.objects.create(government_id=government_id, name=name, nickname=nickname,
                                party_type=organization_type)


def create_person(government_id, first_name, last_name, title, nickname, date_of_birth, comment):
    return Party.objects.create(government_id=government_id, first_name=first_name, last_name=last_name, title=title,
                                nickname=nickname,
                                date_of_birth=date_of_birth, party_type=person_type)
