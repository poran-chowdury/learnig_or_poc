Feature: UI-DB integration

  Scenario Outline: Verify user is able to delete ffm user from UI
    Given I register FFM user with following details
      | firstname   | lastname   | email   | password   | username   |
      | <firstname> | <lastname> | <email> | <password> | <username> |
    And I connect to DB
    Then I verify if ffm user exists in db with email "ffm@test.com" and memberType "1"
    And I launch the UI application
    And I login the UI application with following details
      | email                | password   |
      | testuser@example.com | teststring |
    And I delete ffm user with email "ffm@test.com" from UI
    And I verify ffm user with email "ffm@test.com" is deleted from UI
    And I logout the UI application
    And I connect to DB
    Then I verify ffm user with email "ffm@test.com" is deleted from db

    Examples:
      | firstname | lastname     | email         | password | username |
      | ffm_name  | ffm_lastname | ffm@test.com  | ffm      | ffm      |
      | ffm_name  | ffm_lastname | ffm2@test.com | ffm      | ff2      |

  Scenario Outline: Verify user is able to delete company from UI
    Given I register a company with following details
      | name   | address   | tin   |
      | <name> | <address> | <tin> |
    And I connect to DB
    Then I verify if company exists in db with name "testcompany"
    And I launch the UI application
    And I login the UI application with following details
      | email                | password   |
      | testuser@example.com | teststring |
    And I delete company with name "testcompany" from UI
    And I verify company with name "testcompany" is deleted from UI
    And I logout the UI application
    And I connect to DB
    Then I verify company with name "testcompany" is deleted from db

    Examples:
      | name        | address | tin      |
      | testcompany | test    | test_tin |

  Scenario Outline: Verify user is able to delete company admins from UI
    Given I register a company with following details
      | name            | address | tin                |
      | testss223333233 | test    | 334243sss34454test |
    And I register a company manager with following details
      | firstName   | lastName   | email   | password   | username   | member_type   |
      | <firstName> | <lastName> | <email> | <password> | <username> | <member_type> |
    When I launch the UI application
    And I login the UI application with following details
      | email                | password   |
      | testuser@example.com | teststring |
    And I delete company admin with email "davidmanagerfinal1@email.com" from UI
    And I verify company admin with email "davidmanagerfinal1@email.com" is deleted from UI
    And I logout the UI application
    And I connect to DB
    Then I verify company admin with email "davidmanagerfinal1@email.com" is deleted from db

    Examples:
      | firstName | lastName | email                        | password | username          | member_type |
      | final2    | final2   | davidmanagerfinal1@email.com | david    | davidintegration1 | 2           |






