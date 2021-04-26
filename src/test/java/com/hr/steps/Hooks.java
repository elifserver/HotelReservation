package com.hr.steps;

import com.hr.pages.Base;
import com.hr.utils.DriverManager;
import com.hr.utils.TestUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.junit.AfterClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Hooks extends Base {
    TestUtils utils;
    InputStream stringsIs;
    private static String testName;
    DriverManager driverManager = new DriverManager();

    @Before
    public void beforeTest(Scenario scenario) throws Exception {

        testName = scenario.getName();

        utils = new TestUtils();
        String stringsXmlFileName = "strings/strings.xml";
        stringsIs = getClass().getClassLoader().getResourceAsStream(stringsXmlFileName);
        TestUtils utils = new TestUtils();
        try {
            strings = utils.parseStringXML(stringsIs);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (stringsIs != null) {
                stringsIs.close();
            }
        }
    }

    @After
    public void afterTest(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(sourceFile, new File("Screenshots/" + File.separator + testName + File.separator + "failure.png"));

            } catch (IOException e) {
                e.printStackTrace();
            }
            byte[] screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenShot, "image/png", "testName");
        }
    }


}
