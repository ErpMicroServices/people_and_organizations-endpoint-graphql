# Created by JimBarrows at 11/25/18
Feature: As a user I want to be able to manage data about cases So that I can carry out various business functions

  @party_database
  @party_endpoint
  Scenario: I can view a list of cases
    Given the following types:
      | case        | Customer Complaint |
      | case        | Sales Negotiation  |
      | case status | New                |
    And there are 5 cases with a type of "Customer Complaint" with a status of "New" in the database
    And there are 5 cases with a type of "Sales Negotiation" with a status of "New" in the database
    When I search for all cases
    Then I get 10 cases
    And 5 of them are cases of type "Customer Complaint"
    And 5 of them are cases of type "Sales Negotiation"

  @party_database
  @party_endpoint
  Scenario: I can view a list of cases by type
    Given the following types:
      | case        | Customer Complaint |
      | case        | Sales Negotiation  |
      | case status | New                |
      | case status | Old                |
    And there are 5 cases with a type of "Customer Complaint" with a status of "New" in the database
    And there are 5 cases with a type of "Sales Negotiation" with a status of "Old" in the database
    When I search for cases of type "Customer Complaint"
    Then I get 5 cases
    And 5 of them are cases of type "Customer Complaint"
    And 0 of them are cases of type "Sales Negotiation"

  @party_database
  @party_endpoint
  Scenario: I can view a list of cases by status
    Given the following types:
      | case        | Customer Complaint |
      | case        | Sales Negotiation  |
      | case status | New                |
      | case status | Old                |
    Given there are 5 cases with a type of "Customer Complaint" with a status of "New" in the database
    And there are 5 cases with a type of "Sales Negotiation" with a status of "Old" in the database
    When I search for cases with a status of "New"
    Then I get 5 cases
    And 5 of them are cases in status "New"
    And 0 of them are cases in status "Old"

  @party_database
  @party_endpoint
  Scenario: I can create a case
    Given the following types:
      | case        | Customer Complaint |
      | case status | New                |
    And a case description of "This is a case to be created"
    And a case status of "New"
    And a case type of "Customer Complaint"
    And a case was started at "2024-02-29T05:26:36-07:00"
    When I save the case
    Then the operation was successful
    And the case is in the database

  @party_database
  @party_endpoint
  Scenario: I can read a case by id
    Given the following types:
      | case        | Customer Complaint |
      | case status | New                |
    And a case description of "This is a case to be read by id"
    And a case status of "New"
    And a case type of "Customer Complaint"
    And a case was started at "2024-02-29T05:26:36-07:00"
    And the case is saved to the database
    When I search for the case by id
    Then the operation was successful

  @party_database
  @party_endpoint
  Scenario: I can update a case
    Given the following types:
      | case        | Customer Complaint |
      | case status | New                |
    And a case description of "This is a case update description"
    And a case status of "New"
    And a case type of "Customer Complaint"
    And a case was started at "2024-02-29T05:26:36-07:00"
    And the case is saved to the database
    When I update the case description to "this is what I updated the case description to"
    Then the operation was successful
    And the case is in the database

  @party_database
  @party_endpoint
  Scenario: I can delete a case
    Given the following types:
      | case        | Customer Complaint |
      | case status | New                |
    And a case description of "This case is to be deleted"
    And a case status of "New"
    And a case type of "Customer Complaint"
    And a case was started at "2024-02-29T05:26:36-07:00"
    And the case is saved to the database
    When I delete the case
    Then the operation was successful
    And the case is not in the database

  @party_database
  @party_endpoint
  Scenario: I can add a communication event to a case
    Given the following types:
      | case                       | Customer Complaint     |
      | case status                | New                    |
      | party                      | Person                 |
      | party role                 | Sender                 |
      | party role                 | Receiver               |
      | communication event        | communication event    |
      | party relationship         | Testing                |
      | party relationship status  | Active                 |
      | communication event status | done                   |
      | contact mechanism          | Contact Mechanism Type |
      | priority                   | High                   |
    And a case description of "This is a case to be read by id"
    And a case status of "New"
    And a case type of "Customer Complaint"
    And a case was started at "2024-02-29T05:26:36-07:00"
    And the case is saved to the database
    And there are 2 parties with a type of "Person" in the database
    And party 1 has a party role of "Sender"
    And party 2 has a party role of "Receiver"
    And a party relationship of type "Testing" between party role "Sender" and party role "Receiver" in status "Active" with priority "High"
    And communication events:
      | Started At                | Ended At                  | Note                                      | Type                | Party Relationship From | Party Relationshiuop To | Status | Contact Mechanism Type |
      | 2024-02-29T05:26:36-07:00 | 2024-02-29T05:26:36-07:00 | This is a communicationg event for a case | communication event | 1                       | 2                       | done   | Contact Mechanism Type |
    When I add the communication event to the case
    Then the operation was successful
    And the case contains the communication event

