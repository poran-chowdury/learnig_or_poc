using System;
using RestSharp;

namespace ApiRestSharp.Base
{
    public class Settings
    {
        public Uri baseUrl { get; set; }
        public IRestResponse response { get; set; }
        public IRestRequest request { get; set; }
        public RestClient restClient { get; set; } = new RestClient();
    }
}
