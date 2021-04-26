package com.hr.runners;

import com.hr.pages.Base;
import io.cucumber.java.Scenario;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.apache.logging.log4j.LogManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        plugin = {
                "pretty",
                "json:target/jsonReport/cucumber.json",
                "de.monochromata.cucumber.report.PrettyReports:target/cucumber"
        },
        glue = {"com/hr/steps"},
        monochrome = true,
        tags = ""
)
public class TestRunner extends Base {

        @AfterClass
        public static void after() throws InterruptedException {
                if(driver != null) {
                        driver.close();
                        driver.quit();
                }
        }

}

