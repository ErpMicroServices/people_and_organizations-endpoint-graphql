import collections

from django.test import TestCase
from graphene.test import Client

from party.schema import schema


class OrganizationTestCase(TestCase):
	fixtures = ['user', 'party_data']
	expected_create_response = {
		"data": {
			"createOrganization": {
				"organization": collections.OrderedDict({
					"name": "Chester Tester Co",
					"comment": None
				})
			}
		}
	}

	def test_create_organization(self):
		client = Client(schema)
		executed = client.execute(
			'''mutation create_organization( $input: CreateOrganizationInput!) {createOrganization(input: $input) {organization {id name comment}}}''',
			variables={"input": {"name": "Chester Tester Co"}})
		self.assertIsNotNone(executed['data']['createOrganization']['organization']['id'])
		# The id changes everytime we run this, so nuke it after making sure we have it
		del executed['data']['createOrganization']['organization']['id']
		self.assertEqual(self.expected_create_response['data']['createOrganization'],
										 executed['data']['createOrganization'])
