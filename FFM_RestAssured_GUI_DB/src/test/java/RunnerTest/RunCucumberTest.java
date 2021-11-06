package RunnerTest;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/***
 * cucumber test runner class to run feature files
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/features"}
                , glue = {"stepDefs"})
public class RunCucumberTest {
}
