package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/*
This is a runner class, based on our criteria(tags) it will pick up those scenarios from all locations
and execute them. In plugin option we gave configuration to automatically generate cucumber-pretty report.
dryRun=true - is used to generated steps for unimplemented steps without starting any test.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "step_definitions",
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber-pretty",
                "json:target/cucumber-reports/CucumberTestReport.json"
        },
        tags = "@demoHR",
        dryRun = false
)
public class cukesRunner{
}
