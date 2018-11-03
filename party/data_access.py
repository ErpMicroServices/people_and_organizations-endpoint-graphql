from datetime import datetime, timedelta

from dateutil import parser
from django.core.cache import cache

from party.models.geographic_boundary import GeographicBoundary
from .models.party import ContactMechanism, ContactMechanismToGeographicBoundary, ContactMechanismType, Party, \
	PartyRole, PartyToContactMechanism
from .models.types import ClassificationType, PartyType, PriorityType, RelationshipStatusType, RelationshipType

default_cache_timeout = timedelta(hours=3).seconds


def classification_types():
	if cache.get('classification_types') is None:
		cache.set('classification_types', ClassificationType.objects.all(), default_cache_timeout)
	return cache.get('classification_types')


def contact_mechanism_types():
	if cache.get('contact_mechanism_types') is None:
		cache.set('contact_mechanism_types', ContactMechanismType.objects.all(), default_cache_timeout)
	return cache.get('contact_mechanism_types')


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


def add_contact_mechanism_to_party(party_id: str, end_point: str, contact_mechanism_type_id: str,
																	 from_date: datetime = datetime.now(), thru_date: datetime = None,
																	 comment: str = None, directions: str = None,
																	 geographic_boundary_id: str = None):
	"""

	:param party_id:
	:param end_point:
	:param contact_mechanism_type_id:
	:param from_date:
	:param thru_date:
	:param comment:
	:param directions:
	:param geographic_boundary_id:
	:return:
	"""
	geographic_boundary = None
	if geographic_boundary_id is not None:
		geographic_boundary = GeographicBoundary.objects.get(id=geographic_boundary_id)
	contact_mechanism = ContactMechanism.objects.filter(id=contact_mechanism_type_id).first()
	if contact_mechanism is None:
		contact_mechanism = ContactMechanism.objects.create(end_point=end_point, directions=directions,
																												type=contact_mechanism_types().get(
																													id=contact_mechanism_type_id))
		if geographic_boundary is not None:
			ContactMechanismToGeographicBoundary.objects.create(contact_mechanism=contact_mechanism,
																													geographic_boundary=geographic_boundary)
	return PartyToContactMechanism.objects.create(from_date=from_date, thru_date=thru_date, comment=comment,
																								party=Party.objects.get(id=party_id),
																								contact_mechanism=contact_mechanism)


def find_classification_type_by_id(id):
	return ClassificationType.objects.get(id=id)


def find_party_by_id(id):
	return Party.objects.get(id=id)


def organization_party_types():
	"""
	Return the root of the oranization party types, 'Organization'
	:return: A tree QuerySet with organization as the root
	"""
	if cache.get('organization_party_types') is None:
		cache.set('organization_party_types', PartyType.objects.root_nodes().get(description='Organization'),
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
