package org.people_and_organizations.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty", "html:target/cucumber-report.html"},
		features = {"src/behaviorTest/resources"}
)
public class CucumberIntegrationTest {
}
