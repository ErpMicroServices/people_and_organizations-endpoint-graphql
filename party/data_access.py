from datetime import timedelta

from django.core.cache import cache

from .models.party import Party
from .models.types import PartyType, ClassificationType

default_cache_timeout = timedelta(hours=3).seconds


def organization_party_types():
    if cache.get('organization_party_types') is None:
        cache.set('organization_party_types', PartyType.objects.get(description='Organization').get_family(),
                  default_cache_timeout)
    return cache.get('organization_party_types')


def person_party_types():
    if cache.get('person_party_types') is None:
        cache.set('person_party_types', PartyType.objects.get(description='Person').get_family(), default_cache_timeout)
    return cache.get('person_party_types')


def find_classification_type_by_id(id):
    return ClassificationType.objects.get(id=id)


def find_party_by_id(id):
    return Party.objects.get(id=id)


def create_organization(government_id, name, nickname, comment):
    organization_type = organization_party_types().get_root()
    return Party.objects.create(government_id=government_id, name=name, nickname=nickname,
                                party_type=organization_type, comment=comment)


def create_person(government_id, first_name, last_name, title, nickname, date_of_birth, comment):
    person_type = person_party_types().get_root()
    return Party.objects.create(government_id=government_id, first_name=first_name, last_name=last_name, title=title,
                                nickname=nickname,
                                date_of_birth=date_of_birth, party_type=person_type, comment=comment)
