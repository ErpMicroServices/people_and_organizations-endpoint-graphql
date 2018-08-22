from datetime import timedelta

from dateutil import parser
from django.core.cache import cache

from .models.party import ContactMechanism, Party, PartyRole
from .models.types import ClassificationType, PartyType, PriorityType, RelationshipStatusType, RelationshipType

default_cache_timeout = timedelta(hours=3).seconds


def classification_types():
	print('classification_types')
	if cache.get('classification_types') is None:
		cache.set('classification_types', ClassificationType.objects.all(), default_cache_timeout)
	return cache.get('classification_types')


def create_organization(government_id, name, nickname, comment):
	organization_type = organization_party_types().get_root()
	return Party.objects.create(government_id=government_id, name=name, nickname=nickname,
															party_type=organization_type, comment=comment)


def create_person(government_id: str = None, first_name: str = None, last_name: str = None, title: str = None,
									nickname: str = None, date_of_birth: str = None,
									comment: str = None) -> Party:
	"""
	Create a party with type == Person
	:param government_id: optional
	:param first_name: optional
	:param last_name:
	:param title:
	:param nickname:
	:param date_of_birth:
	:param comment:
	:return: A Party object with an id, and any information provided in the create
	"""
	person_type = person_party_types()
	if date_of_birth is not None:
		date_of_birth = parser.parse(date_of_birth, ignoretz=True)
	return Party.objects.create(government_id=government_id, first_name=first_name, last_name=last_name, title=title,
															nickname=nickname,
															date_of_birth=date_of_birth, party_type=person_type, comment=comment)


def find_classification_type_by_id(id):
	return ClassificationType.objects.get(id=id)


def find_contact_mechanism_type_by_id(id):
	return ContactMechanism.objects.get(id=id)


def find_party_by_id(id):
	return Party.objects.get(id=id)


def organization_party_types():
	if cache.get('organization_party_types') is None:
		cache.set('organization_party_types', PartyType.objects.get(description='Organization').get_family(),
							default_cache_timeout)
	return cache.get('organization_party_types')


def parties_all():
	return Party.objects.all()


def party_relationships():
	return RelationshipStatusType.objects.all()


def relationship_types():
	if cache.get('relationship_types') is None:
		cache.set('relationship_types', RelationshipType.objects.all(), default_cache_timeout)
	return cache.get('relationship_types')


def party_role_types():
	if cache.get('party_role_types') is None:
		cache.set('party_role_types', PartyRole.objects.all(), default_cache_timeout)
	return cache.get('party_role_types')


def party_types():
	"""
	Retrieve a list of party types
	:return: A list of all party types
	"""
	if cache.get('party_types') is None:
		cache.set('party_types', PartyType.objects.all(), default_cache_timeout)
	return cache.get('party_types')


def person_party_types():
	"""
	Retrieve the tree where Person is the root.  Practically speaking, this means only 1
	:return: The party type where 'Person' is the root
	"""
	if cache.get('person_party_types') is None:
		cache.set('person_party_types', PartyType.objects.get(description='Person'), default_cache_timeout)
	return cache.get('person_party_types')


def priority_types():
	if cache.get('priority_types') is None:
		cache.set('priority_types', PriorityType.objects.all(), default_cache_timeout)
	return cache.get('priority_types')
