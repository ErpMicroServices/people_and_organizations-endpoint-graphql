# Created by JimBarrows at 2019-03-07
Feature: As a user I want to manage communication event roles So that I can manage who/what is communicating with

	@party_database
	Scenario: I can add a communication event role to a communication event
		Given the following types:
			| party                      | Person        |
			| party role                 | Sender        |
			| party role                 | Receiver      |
			| communication event        | Find me       |
			| communication event        | Don't find me |
			| communication event role   | Participant   |
			| party relationship         | Testing       |
			| party relationship status  | Active        |
			| communication event status | done          |
			| contact mechanism          | test          |
			| priority                   | High          |
		And there are 2 parties with a type of "Person" in the database
		And party 1 has a party role of "Sender"
		And party 2 has a party role of "Receiver"
		And a party relationship of type "Testing" between party role "Sender" and party role "Receiver" in status "Active" with priority "High"
		And a communication event with a note of "Dont find this"
		And a communication event is for a relationship between party 1 and party 2
		And a communication event status is "done"
		And a communication event contact mechanism type is "test"
		And a communication event has a type of "Don't find me"
		And the communication event is in the database
		When I add party 1 as a having a communication event role of "Participant"
		Then the operation was successful
		And the communication event has party 1 in communication event role of "Participant"

#	@party_endpoint
	Scenario: I can change a communication event role to a communication event
		Given the following types:
			| party                      | Person        |
			| party role                 | Sender        |
			| party role                 | Receiver      |
			| communication event        | Find me       |
			| communication event        | Don't find me |
			| communication event role   | Participant   |
			| party relationship         | Testing       |
			| party relationship status  | Active        |
			| communication event status | done          |
			| contact mechanism          | test          |
			| priority                   | High          |
		And there are 2 parties with a type of "Person" in the database
		And party 1 has a party role of "Sender"
		And party 2 has a party role of "Receiver"
		And a party relationship of type "Testing" between party role "Sender" and party role "Receiver" in status "Active" with priority "High"
		And a communication event with a note of "Dont find this"
		And a communication event is for a relationship between party 1 and party 2
		And a communication event status is "done"
		And a communication event contact mechanism type is "test"
		And a communication event has a type of "Don't find me"
		And the communication event is in the database
		When I change the party 1 to party 2 for communication event role of "Participant"
		Then the operation was successful
		And the communication event has party 2 in communication event role of "Participant"
