using System;
using Newtonsoft.Json;
using NUnit.Framework;
using RestSharp;
using System.Collections.Generic;
using System.Linq;
using ApiRestSharp.Base;
using TechTalk.SpecFlow;
using ApiRestSharp.Model;
using ApiRestSharp.Utilities;

namespace ApiRestSharp.Steps
{
    [Binding]
    class GetUsersSteps
    {
        private readonly Settings _settings;
        public GetUsersSteps(Settings settings) => _settings = settings;

        [Given(@"I perform GET operation for Users ""(.*)""")]
        public void GivenIPerformGETOperationForUsers(string url)
        {
            _settings.request = new RestRequest(url, Method.GET);
        }

        [Then(@"I perform operation for users ""(.*)""")]
        [System.Obsolete]
        public void ThenIPerformOperationForUsers(int userId)
        {
            _settings.request.AddUrlSegment("userId", userId.ToString());
            _settings.response = _settings.restClient.ExecuteAsyncRequest<List<Users>>(_settings.request).GetAwaiter().GetResult();
        }

        [Then(@"I compare users ""(.*)"" with value ""(.*)""")]
        public void ThenICompareUsersWithValue(string key, string value)
        {
            //Assert.That(_settings.response.GetResponseObjectArray(key), Is.EqualTo(value), $"The {key} is not matching");

            var locations = Libraries.DeserializeResponse(_settings.response);

            foreach (var location in locations)
            {
                if (location.Key == key)
                {
                    var username = location.Value;

                    if (username != null)
                        Assert.AreEqual(value, username);
                }
            }
        }

        [Then(@"I compare users address city ""(.*)"" with value ""(.*)""")]
        public void ThenICompareUsersAddressWithValue(string key, string value)
        {
            var addressCity = _settings.response.DeserializeResponse();

            foreach (var city in addressCity)
            {
                if (city.Key == key)
                {
                    var address = JsonConvert.DeserializeObject<Users.Address>(city.Value);

                    if (address != null)
                        Assert.That(address.city, Is.EqualTo(value));
                }
            }

        }

        [Then(@"I compare users on ""(.*)"" filed ""(.*)"" with value ""(.*)""")]
        public void ThenICompareUsersOnFiledWithValue(string key, string filter, string value)
        {
            var addresses = _settings.response.DeserializeResponse();

            foreach (var address in addresses)
            {
                if (address.Key == key)
                {
                    var addressValue = JsonConvert.DeserializeObject<Users.Address>(address.Value);

                    switch (filter)
                    {
                        case "street":
                            if (addressValue != null)
                                Assert.That(addressValue.street, Is.EqualTo(value));
                            break;
                        case "suite":
                            if (addressValue != null)
                                Assert.That(addressValue.suite, Is.EqualTo(value));
                            break;
                        case "city":
                            if (addressValue != null)
                                Assert.That(addressValue.city, Is.EqualTo(value));
                            break;
                        case "zipcode":
                            if (addressValue != null)
                                Assert.That(addressValue.zipcode, Is.EqualTo(value));
                            break;
                  
                    }
                    return;
                }
            }
        }


    }
}
