# Created by JimBarrows at 11/25/18
Feature: As a user I want to be able to manage data about people and groups So that I can carry out various business functions

	@party_database
	@party_endpoint
	Scenario: I can view a list of parties
		Given the following types:
			| party | Person       |
			| party | Organization |
		And there are 5 parties with a type of "Person" in the database
		And there are 5 parties with a type of "Organization" in the database
		When I search for all parties
		Then the operation was successful
		And I get 10 parties
		And 5 of them are type "Person"
		And 5 of them are type "Organization"

	@party_database
	@party_endpoint
	Scenario: I can view a list of parties by type
		Given the following types:
			| party | Person       |
			| party | Organization |
		And there are 5 parties with a type of "Person" in the database
		And there are 5 parties with a type of "Organization" in the database
		When I search for parties of type "Person"
		Then the operation was successful
		And I get 5 parties
		And 5 of them are type "Person"
		And 0 of them are type "Organization"

	@party_database
		@party_endpoint
	Scenario Outline: I can create a party
		Given a type of "<type>" with a description of "<description>" is in the database
		And a comment of "<comment>"
		When I save the party
		Then the operation was successful
		And I get the party back
		And the party is in the database

		Examples:
			| type  | description  | comment                 |
			| party | Person       | This is a person        |
			| party | Organization | This is an organization |
#			| party | Organization |                         |

	@party_database
		@party_endpoint
	Scenario Outline: I can create a party without a comment field
		Given a type of "<type>" with a description of "<description>" is in the database
		And no comment field
		When I save the party
		Then the operation was successful
		And I get the party back
		And the party is in the database

		Examples:
			| type  | description  |
			| party | Person       |
			| party | Organization |
			| party | Organization |

	@party_database
		@party_endpoint
	Scenario Outline: I can read a party by id
		Given a type of "<type>" with a description of "<description>" is in the database
		And a party with a comment of "<comment>" and a type of "<description>" is in the database
		When I search for the party by id
		Then the operation was successful
		And I get the party back

		Examples:
			| type  | description  | comment                 |
			| party | Person       | This is a person        |
			| party | Organization | This is an organization |

	@party_database
		@party_endpoint
	Scenario Outline: I can update a party
		Given a type of "<type>" with a description of "<description>" is in the database
		And a party with a comment of "<comment>" and a type of "<description>" is in the database
		And I change the comment to "<new comment>"
		When I update the party
		Then the operation was successful
		And I get the party back
		And the party is in the database
		And there is 1 party in the database

		Examples:
			| type  | description  | comment                 | new comment                     |
			| party | Person       | This is a person        | This is a modified person       |
			| party | Organization | This is an organization | This is a modified organization |

	@party_database
		@party_endpoint
	Scenario Outline: I can delete a party
		Given a type of "<type>" with a description of "<description>" is in the database
		And a party with a comment of "<comment>" and a type of "<description>" is in the database
		When I delete the party
		Then the operation was successful
		And the party is not in the database

		Examples:
			| type  | description  | comment
			| party | Person       | This is a person
			| party | Organization | This is an organization
