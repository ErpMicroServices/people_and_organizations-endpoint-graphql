import collections
from datetime import date

from django.test import TestCase
from graphene.test import Client

from party.schema import schema


class PersonTests(TestCase):
	fixtures = ['user', 'party_data']
	expected_create_response = {
		"data": {
			"createPerson": {
				"person": collections.OrderedDict({
					"firstName": "Chester",
					"lastName": "Tester",
					"title": 'Testy Person',
					"nickname": 'TestyChesty',
					"dateOfBirth": '1968-01-01'
				})
			}
		}
	}

	def test_create_person(self):
		client = Client(schema)
		executed = client.execute('''mutation create_person($input: CreatePersonInput!) {
		createPerson(input: $input) {
			person {
				id
				firstName
				lastName
				title
				nickname
				dateOfBirth
			}
		}
	}''', variables={'input': {'firstName': 'Chester',
														 'lastName': 'Tester',
														 'title': 'Testy Person',
														 'nickname': 'TestyChesty',
														 'dateOfBirth': date(year=1968, month=1, day=1).isoformat()}})
		self.assertIsNotNone(executed['data']['createPerson']['person']['id'])
		del executed['data']['createPerson']['person']['id']
		self.assertEqual(self.expected_create_response['data']['createPerson'], executed['data']['createPerson'])
