# Created by JimBarrows at 2019-03-06
Feature: As a user I want to add, update, or remove purposes on a communication event So that I can better manage communications

	@party_database
	Scenario: I can add a purpose to a communication event
		Given the following types:
			| communication event         | Incoming call    |
			| communication event purpose | Interrupt dinner |
			| communication event status  | done             |
			| contact mechanism           | test             |
			| party                       | Person           |
			| party relationship          | Testing          |
			| party relationship status   | Active           |
			| party role                  | Sender           |
			| party role                  | Receiver         |
			| priority                    | High             |

		And there are 2 parties with a type of "Person" in the database
		And party 1 has a party role of "Sender"
		And party 2 has a party role of "Receiver"
		And a party relationship of type "Testing" between party role "Sender" and party role "Receiver" in status "Active" with priority "High"
		And a communication event with a note of "This is a note"
		And a communication event is for a relationship between party 1 and party 2
		And a communication event status is "done"
		And a communication event contact mechanism type is "test"
		And a communication event has a type of "Incoming call"
		And the communication event is in the database
		When I add the purpose of "Interrupt dinner"
		Then the operation was successful
		And the communication event has the purpose "Interrupt dinner"

	@party_database
	Scenario: I can change a purpose to a communication event
		Given the following types:
			| communication event         | Incoming call    |
			| communication event purpose | Interrupt dinner |
			| communication event purpose | Annoy receiver   |
			| communication event status  | done             |
			| contact mechanism           | test             |
			| party                       | Person           |
			| party relationship          | Testing          |
			| party relationship status   | Active           |
			| party role                  | Sender           |
			| party role                  | Receiver         |
			| priority                    | High             |
		And there are 2 parties with a type of "Person" in the database
		And party 1 has a party role of "Sender"
		And party 2 has a party role of "Receiver"
		And a party relationship of type "Testing" between party role "Sender" and party role "Receiver" in status "Active" with priority "High"
		And a communication event with a note of "This is a note"
		And a communication event is for a relationship between party 1 and party 2
		And a communication event status is "done"
		And a communication event contact mechanism type is "test"
		And a communication event has a type of "Incoming call"
		And the communication event is in the database
		And the purpose is "Interrupt dinner"
		When I change the purpose to "Annoy receiver"
		Then the operation was successful
		And the communication event has the purpose "Annoy receiver"

	@party_database
	Scenario: I can remove a purpose from a communication event
		Given the following types:
			| communication event         | Incoming call    |
			| communication event purpose | Interrupt dinner |
			| communication event status  | done             |
			| contact mechanism           | test             |
			| party                       | Person           |
			| party relationship          | Testing          |
			| party relationship status   | Active           |
			| party role                  | Sender           |
			| party role                  | Receiver         |
			| priority                    | High             |
		And there are 2 parties with a type of "Person" in the database
		And party 1 has a party role of "Sender"
		And party 2 has a party role of "Receiver"
		And a party relationship of type "Testing" between party role "Sender" and party role "Receiver" in status "Active" with priority "High"
		And a communication event with a note of "This is a note"
		And a communication event is for a relationship between party 1 and party 2
		And a communication event status is "done"
		And a communication event contact mechanism type is "test"
		And a communication event has a type of "Incoming call"
		And the communication event is in the database
		And the purpose is "Interrupt dinner"
		When I remove the purpose "Interrupt dinner"
		Then the operation was successful
		And the communication event has no purpose
