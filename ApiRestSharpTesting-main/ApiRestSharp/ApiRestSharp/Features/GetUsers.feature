Feature: GetUsers
	Get Users API Testing

Scenario: Get Users Test 1
	Given I perform GET operation for Users "users/{userId}"
	Then I perform operation for users "1"
	And I compare users "username" with value "Bret"

Scenario: Get Users Test 2
	Given I perform GET operation for Users "users/{userId}"
	Then I perform operation for users "1"
	And I compare users address city "address" with value "Gwenborough"

Scenario: Get Users Test 3
	Given I perform GET operation for Users "users/{userId}"
	Then I perform operation for users "1"
	And I compare users on "address" filed "city" with value "Gwenborough"

Scenario: Get Users Test 4
	Given I perform GET operation for Users "users/{userId}"
	Then I perform operation for users "1"
	And I compare users on "address" filed "zipcode" with value "92998-3874"

Scenario: Get Users Test 5 Failed
	Given I perform GET operation for Users "users/{userId}"
	Then I perform operation for users "2"
	And I compare users on "address" filed "street" with value "Victor Plains Failed"