Feature: GetPosts
	Get Post API Testing

Scenario: Get Post Test 1
	Given I perform GET operation for Post "posts/{postId}"
	Then I perform operation for post "1"
	And I compare post "title" with value "sunt aut facere repellat provident occaecati excepturi optio reprehenderit"

