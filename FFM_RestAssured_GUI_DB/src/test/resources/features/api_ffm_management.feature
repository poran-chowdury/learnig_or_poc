Feature: FFM Management

  Scenario Outline: Verify user is able to register a FFM user in bulk
    Given I register FFM user "<firstName>" "<lastName>" "<email>" "<password>" "<username>"
    Then I verify FFM user is created with status code "201" and "success" message
    Examples:
      | firstName | lastName   | email                       | password  | username         |
      | David     | Williamson | david.williamson@email.com! | tEst%$@1! | david.williamson |
      | David     | Williamson | david.williamson@email.com! | tEst%$@1! | david.williamson |
      | David     | Williamson | david.williamson@email.com! | tEst%$@1! | david.williamson |
      | David     | Williamson | david.williamson@email.com! | tEst%$@1! | david.williamson |

  Scenario Outline: Create company and register company manager under the same company in bulk
    Given I register a company with "<name>" "<address>" "<tin>"
    Then I verify company is created with status code "201" and "success" message
    When I register a company manager with "<firstName>" "<lastName>" "<email>" "<password>" "<username>" "<member_type>"
    Then I verify company manager is registered with status code "201" and "success" message
    Examples:
      | name | address | tin | firstName | lastName   | email                       | password  | username         | member_type |
      | test | test    | 122 | David     | Williamson | david.williamson@email.com! | tEst%$@1! | david.williamson | 2           |
      | 3233 | 34      | 44  | David     | Williamson | david.williamson@email.com! | tEst%$@1! | david.williamson | 2           |
