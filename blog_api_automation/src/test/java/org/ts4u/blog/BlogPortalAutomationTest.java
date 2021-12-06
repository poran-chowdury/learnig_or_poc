package org.ts4u.blog;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * @author TOWFIQUL ISLAM
 * @email towfiq.106@gmail.com
 * @since 11/25/21 11:18 PM
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/features"}, glue = {"org.ts4u.blog.stepDefs"})

/*This is main class which will run all the features or tests*/
public class BlogPortalAutomationTest {
}
