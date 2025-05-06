Feature: As a user I want to be able to manage id data about people and groups So that I can carry out various business functions

  Scenario: I can create a party with multiple ids
    Given the following types:
      | id | Government      |
      | id | Sales           |
      | id | Customer Number |
    And party ids of
      | Government      | 666  |
      | Sales           | 777  |
      | Customer Number | 4377 |
    And a type of "party" with a description of "Person" is in the database
    When I save the party
    Then the operation was successful
    And I get the party back
    And the party is in the database
    And the party id type is present
    And the party ids are present

  Scenario: I can add a id to a party
    Given the following types:
      | party | Person     |
      | id    | Government |
    And a party of type "Person" is in the database
    When I add the "Government" id of "1234" to the party
    Then the operation was successful
    And I get the party id back
    Then the party id is in the database

  Scenario: I can update an id in a party
    Given the following types:
      | party | Person     |
      | id    | Government |
    And a party of type "Person" is in the database
    And the id "123456" of "Government" type is in the party
    When I change the id to "7890"
    Then the operation was successful
    And the party is in the database
    And the party id is in the database


  Scenario: I can delete an id in a party
    Given the following types:
      | party | Person     |
      | id    | Government |
    And a party of type "Person" is in the database
    And the id "123456" of "Government" type is in the party
    When I delete the id
    Then the operation was successful
    And the party id is not in the database
    And the party is in the database
