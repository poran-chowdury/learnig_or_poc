Feature: Create FFM OT Member

  Scenario: FFM OT Member should create another OT member

    Given As an FFM OT member I should logged in the FFM platform
    When I click on the add super admin span
    When I should input all the required fields
    When I click on the register button
    Then It should creates an OT member

