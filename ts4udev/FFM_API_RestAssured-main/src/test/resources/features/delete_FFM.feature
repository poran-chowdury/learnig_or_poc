Feature: Delete FFM User

  Scenario: Verify user is able to delete FFM user
    Given I register FFM user with following details
      | firstname | lastname    | email       | password | username |
      | ffmname   | ffmlastname | ff@ffm1.com | ffm      | ffmuser1 |
    And I login with following details
      | email       | password |
      | ff@ffm1.com | ffm      |
    #When I delete the FFM user
    #Then the user should be deleted with status code "200" and message "success"