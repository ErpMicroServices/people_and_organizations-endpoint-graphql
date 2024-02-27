package org.erpmicroservices.endpoint.graphql;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty", "html:target/cucumber-report.html"},
		features = {"../people_and_organizations-features"}
)
public class CucumberIntegrationTest {
}
