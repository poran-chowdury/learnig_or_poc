Feature: FFM Logout

  Scenario: Verify user is able to logout as FFM user
    Given I register FFM user with following details
      | firstname | lastname      | email         | password | username   |
      | ffmn16ame | ffml16astname | ff16@ffm1.com | ff11m    | ff16muser1 |
    And I login with following details
      | email         | password |
      | ff16@ffm1.com | ff11m    |
    When I logout
    Then I should be able to logout