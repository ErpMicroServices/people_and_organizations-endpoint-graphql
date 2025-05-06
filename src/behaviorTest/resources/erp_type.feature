Feature: As a user of the People and Organization Entity Domain I want to manage types So that I can manage various types of things in the domain

	@party_ui
		@party_database
	Scenario Outline: Create a type
		Given a type of "<type>"
		Given a type with a description of "<description>"
		When I save the type
		Then the type is in the database

		Examples:
			| type                        | description   |
			| case                        | complaint     |
			| case role                   | participant   |
			| case status                 | new           |
			| communication event         | phone call    |
			| communication event purpose | fun           |
			| communication event role    | participant   |
			| communication event status  | over          |
			| contact mechanism           | phone         |
			| facility                    | warehouse     |
			| facility role               | destination   |
			| geographic boundary         | county        |
			| id                          | 123456xyz     |
			| name                        | first name    |
			| party                       | Chester       |
			| party classification        | Super huge    |
			| party relationship          | Customer      |
			| party relationship status   | b0rken        |
			| party role                  | ChesterTester |
			| priority                    | High          |

	@party_ui
		@party_database
	Scenario Outline: Read a type by description
		Given a type of "<type>" with a description of "<description>" is in the database
		When I search for the type by the description "<description>"
		Then I find the type
		Examples:
			| type                        | description   |
			| case                        | complaint     |
			| case status                 | new           |
			| case role                   | participant   |
			| communication event         | phone call    |
			| communication event purpose | fun           |
			| communication event role    | participant   |
			| communication event status  | over          |
			| contact mechanism           | phone         |
			| facility                    | warehouse     |
			| facility role               | destination   |
			| geographic boundary         | county        |
			| id                          | 123456xyz     |
			| name                        | first name    |
			| party                       | Chester       |
			| party classification        | Super huge    |
			| party relationship          | Customer      |
			| party relationship status   | b0rken        |
			| party role                  | ChesterTester |
			| priority                    | High          |
#
#	@party_ui
#  @party_database
#  Scenario Outline: Update a type
#	Given a type of "<type>" with a description of "<description>" is in the database
#	When I update the description of the type to "<udpated description>"
#	Then the type description has been updated
#	Examples:
#	  | type                        | description   | udpated description  |
#	  | case                        | complaint     | update complaint     |
#	  | case status                 | new           | update new           |
#	  | case role                   | participant   | update participant   |
#	  | communication event         | phone call    | update phone call    |
#	  | communication event purpose | fun           | update fun           |
#	  | communication event role    | participant   | update participant   |
#	  | communication event status  | over          | update over          |
#	  | contact mechanism           | phone         | update phone         |
#	  | facility                    | warehouse     | update warehouse     |
#	  | facility role               | destination   | update destination   |
#	  | geographic boundary         | county        | update county        |
#	  | id                          | 123456xyz     | update 123456xyz     |
#	  | name                        | first name    | update first name    |
#	  | party                       | Chester       | update Chester       |
#	  | party classification        | Super huge    | update Super Huge    |
#	  | party relationship          | Customer      | update Customer      |
#	  | party relationship status   | b0rken        | update b0rken        |
#	  | party role                  | ChesterTester | update ChesterTester |
#	  | priority                    | High          | update Hihgh         |
#
#	@party_ui
#  @party_database
#  Scenario Outline: Delete a party role type
#	Given a type of "<type>" with a description of "<description>" is in the database
#	When I delete the type
#	Then the type is not in the database
#	Examples:
#	  | type                        | description   |
#	  | case                        | complaint     |
#	  | case status                 | new           |
#	  | case role                   | participant   |
#	  | communication event         | phone call    |
#	  | communication event purpose | fun           |
#	  | communication event role    | participant   |
#	  | communication event status  | over          |
#	  | contact mechanism           | phone         |
#	  | facility                    | warehouse     |
#	  | facility role               | destination   |
#	  | geographic boundary         | county        |
#	  | id                          | 123456xyz     |
#	  | name                        | first name    |
#	  | party                       | Chester       |
#	  | party classification        | Super huge    |
#	  | party relationship          | Customer      |
#	  | party relationship status   | b0rken        |
#	  | party role                  | ChesterTester |
#	  | priority                    | High          |
#
#	@party_ui
#  @party_database
#  Scenario Outline: I can create a child of an erp type
#	Given a type of "<type>" with a description of "<description>" is in the database
#	When I add a child type with a description of "<child description>"
#	Then I can find the parent of the child  of the type
#	Examples:
#	  | type                        | description   | child description   |
#	  | case                        | complaint     | child complaint     |
#	  | case status                 | new           | child new           |
#	  | case role                   | participant   | child participant   |
#	  | communication event         | phone call    | child phone call    |
#	  | communication event purpose | fun           | child fun           |
#	  | communication event role    | participant   | child participant   |
#	  | communication event status  | over          | child over          |
#	  | contact mechanism           | phone         | child phone         |
#	  | facility                    | warehouse     | child warehouse     |
#	  | facility role               | destination   | child destination   |
#	  | geographic boundary         | county        | child county        |
#	  | id                          | 123456xyz     | child 123456xyz     |
#	  | name                        | first name    | child first name    |
#	  | party                       | Chester       | child Chester       |
#	  | party classification        | Super huge    | child Super huge    |
#	  | party relationship          | Customer      | child Customer      |
#	  | party relationship status   | b0rken        | child b0rken        |
#	  | party role                  | ChesterTester | child ChesterTester |
#
#	@party_ui
#  @party_database
#  Scenario Outline: I can read a parent type, and get the children
#	Given a type of "<type>" with a description of "<description>" is in the database
#	And I add a child type with a description of "ChesterTester Child"
#	When I search for the parent type
#	Then I find the type
#	And I find the child type
#
#	Examples:
#	  | type                        | description   |
#	  | case                        | complaint     |
#	  | case status                 | new           |
#	  | case role                   | participant   |
#	  | communication event         | phone call    |
#	  | communication event purpose | fun           |
#	  | communication event role    | participant   |
#	  | communication event status  | over          |
#	  | contact mechanism           | phone         |
#	  | facility                    | warehouse     |
#	  | facility role               | destination   |
#	  | geographic boundary         | county        |
#	  | id                          | 123456xyz     |
#	  | name                        | first name    |
#	  | party                       | Chester       |
#	  | party classification        | Super huge    |
#	  | party relationship          | Customer      |
#	  | party relationship status   | b0rken        |
#	  | party role                  | ChesterTester |
#	  | priority                    | High          |
