using System;
using System.IO;
using ApiRestSharp.Base;
using AventStack.ExtentReports;
using AventStack.ExtentReports.Gherkin.Model;
using AventStack.ExtentReports.Reporter;
using TechTalk.SpecFlow;
//using NUnit.Framework;

//[assembly: Parallelizable(ParallelScope.Fixtures)]

namespace ApiRestSharp.Hooks
{
    [Binding]
    public class TestInitialize
    {
        //Global Variable for Extend report
        private static ExtentTest _feature;
        private static ExtentTest _scenario;
        private static ExtentReports _extent;

        private readonly Settings _settings;

        public TestInitialize(Settings settings)
        {
            _settings = settings;
        }

        [BeforeScenario]
        [Obsolete]
        public void TestSetup()
        {
            _settings.baseUrl = new Uri("https://jsonplaceholder.typicode.com/");
            _settings.restClient.BaseUrl = _settings.baseUrl;

          
        }

        [BeforeTestRun]
        public static void InitializeReport()
        {
            var path = Path.Combine(@"C:\Temp\ApiRestSharp\");
            //Initialize Extent report before test starts
            var htmlReporter = new ExtentHtmlReporter(path);
            htmlReporter.Config.Theme = AventStack.ExtentReports.Reporter.Configuration.Theme.Dark;
            //Attach report to reporter
            _extent = new ExtentReports();
            _extent.AttachReporter(htmlReporter);
        }

        [BeforeFeature]
        [Obsolete]
        public static void BeforeFeature()
        {
            //Create dynamic feature name
            _feature = _extent.CreateTest<Feature>(FeatureContext.Current.FeatureInfo.Title);

        }

        [BeforeScenario]
        [Obsolete]
        public void Initialize()
        {
          
            //Create dynamic scenario name
            _scenario = _feature.CreateNode<Scenario>(ScenarioContext.Current.ScenarioInfo.Title);
        }

        [AfterStep]
        [Obsolete]
        public void InsertReportingSteps()
        {
            var stepType = ScenarioStepContext.Current.StepInfo.StepDefinitionType.ToString();

            if (ScenarioContext.Current.TestError == null)
            {
                if (stepType == "Given")
                    _scenario.CreateNode<Given>(ScenarioStepContext.Current.StepInfo.Text);
                else if (stepType == "When")
                    _scenario.CreateNode<When>(ScenarioStepContext.Current.StepInfo.Text);
                else if (stepType == "Then")
                    _scenario.CreateNode<Then>(ScenarioStepContext.Current.StepInfo.Text);
                else if (stepType == "And")
                    _scenario.CreateNode<And>(ScenarioStepContext.Current.StepInfo.Text);
            }
            else if (ScenarioContext.Current.TestError != null)
            {
                if (stepType == "Given")
                    _scenario.CreateNode<Given>(ScenarioStepContext.Current.StepInfo.Text).Fail(ScenarioContext.Current.TestError.InnerException);
                else if (stepType == "When")
                    _scenario.CreateNode<When>(ScenarioStepContext.Current.StepInfo.Text).Fail(ScenarioContext.Current.TestError.InnerException);
                else if (stepType == "Then")
                    _scenario.CreateNode<Then>(ScenarioStepContext.Current.StepInfo.Text).Fail(ScenarioContext.Current.TestError.Message);
            }
        }

        [AfterTestRun]
        public static void TearDownReport()
        {
            //Flush report once test completes
            _extent.Flush();
        }
    }
}
