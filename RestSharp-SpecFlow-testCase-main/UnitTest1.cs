using NUnit.Framework;
using RestSharp;
using RestSharpWithSpecFlow.Model;
using System.Collections.Generic;


namespace RestSharpWithSpecFlow
{
    [TestFixture]
    public class Tests
    {
        [Test]
        public void TestGetRequest()
        {
            var restClient = new RestClient("http://localhost:3000/");
            var request = new RestRequest("posts/{postid}", Method.GET);
            request.AddUrlSegment("postid", 1);
            var response = restClient.Execute(request);
            var deserialize = new RestSharp.Serialization.Json.JsonDeserializer();
            var outputData = deserialize.Deserialize<Dictionary<string, string>>(response);
            var result = outputData["author"];
            Assert.That(result, Is.EqualTo("typicode"), "Author is not correct");
        }
        [Test]
        public void TestPostRequest()
        {
            var restClient = new RestClient("http://localhost:3000/");
            var request = new RestRequest("posts/{postid}/profile", Method.POST);
            request.AddJsonBody(new { name = "raju" });
            request.AddUrlSegment("postid", 3);
            var response = restClient.Execute(request);
            var deserialize = new RestSharp.Serialization.Json.JsonDeserializer();
            var outputData = deserialize.Deserialize<Dictionary<string, string>>(response);
            var result = outputData["name"];
            Assert.That(result, Is.EqualTo("raju"), "Author is not correct");
        }
        // [Test]
        // public void TestPostRequestWithModelClass()
        // {
        //     var restClient = new RestClient("http://localhost:3000/");
        //     var request = new RestRequest("posts", Method.POST);
        //     request.AddJsonBody(new Posts() { id = "22", author = "Rezaul", title = "Tutorial" });
        //     var response = restClient.Execute(request);
        //     var deserialize = new RestSharp.Serialization.Json.JsonDeserializer();
        //     var outputData = deserialize.Deserialize<Dictionary<string, string>>(response);
        //     var result = outputData["author"];
        //     Assert.That(result, Is.EqualTo("Rezaul"), "Author is not correct");
        // }
    }
}