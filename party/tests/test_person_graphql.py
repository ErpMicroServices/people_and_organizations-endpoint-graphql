import collections
from datetime import date

from django.test import TestCase
from graphene.test import Client

from party.schema import schema


class PersonTests(TestCase):
	client = None
	fixtures = ['user', 'party_data', "test_party_data"]
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

	def setUp(self):
		self.client = Client(schema);

	def test_create_person(self):
		executed = self.client.execute(
			'''mutation create_person($input: CreatePersonInput!) {createPerson(input: $input) { person { id firstName lastName title nickname dateOfBirth}}}''',
			variables={'input': {'firstName': 'Chester',
													 'lastName': 'Tester',
													 'title': 'Testy Person',
													 'nickname': 'TestyChesty',
													 'dateOfBirth': date(year=1968, month=1, day=1).isoformat()}})
		self.assertIsNotNone(executed['data']['createPerson']['person']['id'])
		del executed['data']['createPerson']['person']['id']
		self.assertEqual(self.expected_create_response['data']['createPerson'], executed['data']['createPerson'])

	def test_add_contact_mechanism_to_person(self):
		executed = self.client.execute(
			'''
mutation add_contact_mechanism($input: AddContactMechanismToPartyInput!) {
  addContactMechanism(input: $input) {
    partyContactMechanism {
      id
      fromDate
      thruDate
      comment
      contactMechanism {
        id
        endPoint
        directions
        type{
          id
          description
        }
      }
      party {
        id
        firstName
        lastName
        name
      }
    }
  }
}
''',
			variables={"input": {"partyId": "2460edfd-d72e-4d7c-b163-9e0d289f84f4",
													 "fromDate": "2008-09-15T15:53:00+05:00",
													 "thruDate": "2009-09-15T15:53:00+05:00",
													 "comment": "This is a comment",
													 "endPoint": "An endpoint",
													 "directions": "Directions to the end point",
													 "contactMechanismTypeId": "037cf887-3829-4153-ba9b-38380b9d1c3f",
													 "geographicBoundaryId": "ed4c7ca6-af95-11e8-a653-53c2fd59a3df"
													 }
								 })
		partyContactMechanism = executed['data']['addContactMechanism']['partyContactMechanism']
		self.assertIsNotNone(partyContactMechanism['id'])
		self.assertIsNotNone(partyContactMechanism['contactMechanism'])
		self.assertIsNotNone(partyContactMechanism['party'])
		self.assertEquals("An endpoint", partyContactMechanism['contactMechanism']['endPoint'])
		self.assertIsNotNone(partyContactMechanism['party']['id'])
