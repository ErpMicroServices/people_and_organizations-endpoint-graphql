from django.test import TestCase

from party.data_access import person_party_types


class DataAccessTests(TestCase):
	fixtures = ['party_data']

	def test_person_party_types(self):
		self.assertEquals('Person', person_party_types().description)
