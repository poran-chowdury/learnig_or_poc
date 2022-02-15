Feature: Buyer Registration

  Scenario Outline: Register Buyer and Check the OTP
    Given Register a buyer with "<name>" "<email>" "<password>"
    When Check the buyer is created with status code "200" and OTP send "true" with this "<email>"
    Then Make otp is verified "true" for "<email>"

    Examples:
    |name|email|password|
    |Test Buyer| test@domain.com| test@123|


    Scenario Outline: Validate Registered Buyer Information From the Database
      Given I connect to the DB
      When Validate with collection name "<email>"
      Then close the db

      Examples:
      |email|
      |test@domain.com|