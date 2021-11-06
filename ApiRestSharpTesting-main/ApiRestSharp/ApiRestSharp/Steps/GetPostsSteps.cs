using System;
using System.Threading;
using RestSharp;
using ApiRestSharp.Base;
using ApiRestSharp.Model;
using ApiRestSharp.Utilities;
using NUnit.Framework;
using TechTalk.SpecFlow;

namespace ApiRestSharp.Steps
{
    [Binding]
    class GetPostsSteps
    {
        private readonly Settings _settings;
        public GetPostsSteps(Settings settings) => _settings = settings;

        [Given(@"I perform GET operation for Post ""(.*)""")]
        public void GivenIPerformGETOperationForPost(string url)
        {
            _settings.request = new RestRequest(url, Method.GET);
        }


        [Then(@"I perform operation for post ""(.*)""")]
        [Obsolete]
        public void ThenIPerformOperationForPost(int postId)
        {
            _settings.request.AddUrlSegment("postId", postId.ToString());
            _settings.response = _settings.restClient.ExecuteAsyncRequest<Posts>(_settings.request).GetAwaiter().GetResult();
        }


        [Then(@"I compare post ""(.*)"" with value ""(.*)""")]
        public void ThenIShouldSeeTheNameAs(string key, string value)
        {
            Assert.That(_settings.response.GetResponseObject(key), Is.EqualTo(value), $"The {key} is not matching");
       
        }



    }
}
