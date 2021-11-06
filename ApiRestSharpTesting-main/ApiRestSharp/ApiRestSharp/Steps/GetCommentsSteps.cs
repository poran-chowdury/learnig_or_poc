using System;
using System.Threading;
using ApiRestSharp.Base;
using ApiRestSharp.Model;
using ApiRestSharp.Utilities;
using NUnit.Framework;
using RestSharp;
using TechTalk.SpecFlow;

namespace ApiRestSharp.Steps
{
    [Binding]
    class GetCommentsSteps
    {
        private readonly Settings _settings;
        public GetCommentsSteps(Settings settings) => _settings = settings;

        [Given(@"I perform GET operation for Comments ""(.*)""")]
        public void GivenIPerformGETOperationForComments(string url)
        {
            _settings.request = new RestRequest(url, Method.GET);
        }

        [Then(@"I perform operation for comments ""(.*)""")]
        [Obsolete]
        public void ThenIPerformOperationForComments(int commentsId)
        {

            _settings.request.AddUrlSegment("commentsId", commentsId.ToString());
            _settings.response = _settings.restClient.ExecuteAsyncRequest<Comments>(_settings.request).GetAwaiter().GetResult();
        }

        [Then(@"I compare comments ""(.*)"" with value ""(.*)""")]
        public void ThenICompareCommentsWithValue(string key, string value)
        {
            Assert.That(_settings.response.GetResponseObject(key), Is.EqualTo(value), $"The {key} is not matching");
        }


    }
}
