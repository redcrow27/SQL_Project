Feature: Query database demoHR
  Background: Connetting to database
    Given user establish connection to database "demoHR"

  @demoHR
  Scenario: postgres testing demoHR departments
    Then user executes query "select * from departments" and verifies result "Finance"
    Then user executes query "select * from employees" and verifies column name "first_name" and result value "Mary"
    Then user gets total row count for query "Select * from departments"
    And user closes connection to database


  @aa
  Scenario: practice
    Given user establish connection to database "demoHR"
    Then User1 executes query "select * from employees" and verifies column name "first_name" and result value "Mary"
    And user closes connection to database