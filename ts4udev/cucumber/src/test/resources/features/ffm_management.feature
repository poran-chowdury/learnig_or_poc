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
