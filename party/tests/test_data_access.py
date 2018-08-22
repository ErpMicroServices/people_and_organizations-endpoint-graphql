from django.test import TestCase

from party.data_access import organization_party_types, person_party_types


class DataAccessTests(TestCase):
	fixtures = ['party_data']

	def test_person_party_types(self):
		self.assertEquals('Person', person_party_types().description)

	def test_organization_party_types(self):
		opt = organization_party_types()
		self.assertEquals('Organization', opt.get_root().description)
		self.assertEquals(['Legal Organization', 'Informal Organization'],
											list(map(lambda c: c.description, opt.get_children())))
