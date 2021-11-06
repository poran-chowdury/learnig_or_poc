Feature: GetComments
	Get Comments API Testing

Scenario: Get Comments Test 1
	Given I perform GET operation for Comments "comments/{commentsId}"
	Then I perform operation for comments "1"
	And I compare comments "email" with value "Eliseo@gardner.biz"
