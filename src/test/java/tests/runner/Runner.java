package tests.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        plugin = {
                "pretty",
                "html:build/cucumber-html/index.html",
                "json:build/cucumber.json",
                "timeline:build/timeReport"},
        glue = {
                "src/test/java/tests/stepdefinitions/StepDefinitions.java"
        },
        tags = ""
)

public class Runner {
}
