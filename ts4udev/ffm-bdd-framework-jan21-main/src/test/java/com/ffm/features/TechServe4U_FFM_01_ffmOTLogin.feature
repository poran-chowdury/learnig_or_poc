Feature: FFM OT Member Login Panel
  @FunctionalTest
Scenario: FFM OT Member should login successfully
  Given As an FFM OT member I should give the baseURl to navigate the login panel
  When I will input registered email and password
  When Click on the login button
  Then I should be able to navigate to the FFM platform

  Scenario Outline: FFM OT Member should login successfully through datadriven

    Given As an FFM OT member I should give the baseURl to navigate the login panel
    When I will input registered "<email>" and "<password>"
    When Click on the login button
    Then I should be able to navigate to the FFM platform

    Examples:
    |email                       |password      |
    |user@example.com            |string        |
    |user@example.com            |string        |
    |user@example.com            |string        |


