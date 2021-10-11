package ecom;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * @author TOWFIQUL ISLAM
 * @since 31/07/2021 01:54
 */

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/features"}, glue = {"ecom.stepDefs"})
public class ECommerceTest {
}
