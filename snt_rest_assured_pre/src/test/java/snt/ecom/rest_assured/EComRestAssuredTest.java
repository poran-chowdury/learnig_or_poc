package snt.ecom.rest_assured;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * @author TOWFIQUL ISLAM
 * @since 31/07/2021 01:54
 */

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/features"}, glue = {"snt.ecom.rest_assured.stepDefs"})
public class EComRestAssuredTest {
}
