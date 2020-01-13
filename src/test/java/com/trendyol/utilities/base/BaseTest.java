package com.trendyol.utilities.base;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected static WebDriver webDriver;

    private static void chromeDriverAndFirefoxSettings(){
        DesiredCapabilities capabilities;
        if (System.getProperties().getProperty("environment").equals("webChrome")){
            System.setProperty("webdriver.chrome.driver", "properties/driver/chromedriver.exe");
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("test-type");
            chromeOptions.addArguments("disable-popup-blocking");
            chromeOptions.addArguments("ignore-certificate-errors");
            chromeOptions.addArguments("disable-translate");
            chromeOptions.addArguments("start-maximized");
            capabilities = DesiredCapabilities.chrome();
            capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
            capabilities.setCapability("acceptSslCerts", "true");
            capabilities.setBrowserName("chrome");
            capabilities.setPlatform(Platform.getCurrent());

            webDriver = new ChromeDriver(capabilities);
            System.out.println("Suite Before Run : " + "Web Local Chrome Browser");
        }else if (System.getProperties().getProperty("environment").equals("webFirefox")){
            System.setProperty("webdriver.gecko.driver", "properties/driver/geckodriver.exe");
            capabilities = DesiredCapabilities.firefox();
            capabilities.setCapability("acceptSslCerts", "true");
            capabilities.setBrowserName("firefox");
            capabilities.setPlatform(Platform.getCurrent());

            webDriver = new FirefoxDriver();
            System.out.println("Suite Before Run : " + "Web Local Firefox Browser");
        }
    }

    @BeforeClass(alwaysRun = true)
    public static void setUp() {
        chromeDriverAndFirefoxSettings();
        System.out.println("Trendyol Test Automation");
        System.out.println("Installation finish");
    }

    @BeforeMethod
    public static void beforeMethod(){
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
    }

    @AfterMethod
    public static void afterMethod(){
        webDriver.manage().deleteAllCookies();
    }

    @AfterClass(alwaysRun = true)
    public static void tearDown(){
        webDriver.close();
        System.out.println("Driver close");
    }
}