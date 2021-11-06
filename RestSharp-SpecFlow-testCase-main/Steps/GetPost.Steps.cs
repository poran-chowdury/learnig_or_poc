using System.Collections.Generic;
using RestSharp;
using NUnit.Framework;
using TechTalk.SpecFlow;
using System;

namespace RestSharpWithSpecFlow.Steps
{
    [Binding]
    public class GetPostSteps
    {
        public RestClient restClient = new RestClient("http://localhost:3000/");
        public RestRequest restRequest = new RestRequest();

        [Given(@"I perform GET operation for ""(.*)""")]
        public void GivenIPerformGETOperationFor(string url)
        {
            restRequest = new RestRequest(url, Method.GET);
            Console.WriteLine("Call");
        }

        [Given(@"I perform operation for post ""(.*)""")]
        public void GivenIPerformOperationForPost(int postId)
        {
            restRequest.AddUrlSegment("postid", postId.ToString());
            var response = restClient.Execute(restRequest);
        }
        [Then(@"I should see the ""(.*)"" name as ""(.*)""")]
        public void ThenIShouldSeeTheNameAs(string key, string value)
        {
            Console.WriteLine("key: " + key);


            var response = restClient.Execute(restRequest);
            var deserialize = new RestSharp.Serialization.Json.JsonDeserializer();
            var outputData = deserialize.Deserialize<Dictionary<string, string>>(response);
            var result = outputData["author"];
            Console.WriteLine("Result: " + result);
            Assert.That(result, Is.EqualTo(value), "Author is not correct");
        }
    }
}