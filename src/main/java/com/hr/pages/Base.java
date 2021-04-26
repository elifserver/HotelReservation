package com.hr.pages;

import com.hr.utils.DriverManager;
import com.hr.utils.TestUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

public class Base {
    DriverManager driverManager = new DriverManager();
    public static WebDriver driver;
    public static WebDriverWait wait;

    protected static HashMap<String, String> strings = new HashMap<String, String>();

    public Base() {
        if(driver == null) {
            try {
                driver = driverManager.getDriver();
                driver.manage().window().maximize();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        PageFactory.initElements(driver, this);
    }

    public void waitForMilliseconds(int milliSec){
        try {
            Thread.sleep(milliSec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
