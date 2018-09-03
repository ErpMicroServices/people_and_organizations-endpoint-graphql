from datetime import datetime, timedelta

import pytz
from django.test import TestCase

from party.data_access import add_contact_mechanism_to_party, organization_party_types, person_party_types


class DataAccessTests(TestCase):
	fixtures = ['party_data', "test_party_data"]

	def test_person_party_types(self):
		self.assertEquals('Person', person_party_types().description)

	def test_organization_party_types(self):
		opt = organization_party_types()
		self.assertEquals('Organization', opt.get_root().description)
		self.assertEquals(['Legal Organization', 'Informal Organization'],
											list(map(lambda c: c.description, opt.get_children())))

	def test_add_contact_mechanism_to_party(self):
		now = datetime.utcnow()
		later = pytz.UTC.localize(now + timedelta(days=365))
		contact_mechanism = add_contact_mechanism_to_party(
			party_id="2460edfd-d72e-4d7c-b163-9e0d289f84f4",
			from_date=now,
			thru_date=later,
			comment="This is a comment",
			end_point="An endpoint",
			directions="Directions to the end point",
			contact_mechanism_type_id="037cf887-3829-4153-ba9b-38380b9d1c3f",
			geographic_boundary_id="ed4c7ca6-af95-11e8-a653-53c2fd59a3df"
		)
		self.assertEquals("2460edfd-d72e-4d7c-b163-9e0d289f84f4", str(contact_mechanism.party.id))
