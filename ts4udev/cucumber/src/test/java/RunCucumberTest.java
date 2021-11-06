import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * @author TOWFIQUL ISLAM
 * @since 25/07/2021 20:20
 */

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/features"}, glue = {"stepDefs"})
public class RunCucumberTest {
}
