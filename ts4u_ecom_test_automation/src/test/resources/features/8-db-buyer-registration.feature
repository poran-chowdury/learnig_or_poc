Feature: DB Test for  Customer Registration

#    DB CHECK
    Scenario Outline: Check in DB and Delete Data
        Given I connect to DB
        Then Check with collection name "<email>"
#        Then I delete DB data
        Then I close DB
        Examples:
            | email                |
            | towfiq.106@gmail.com |
