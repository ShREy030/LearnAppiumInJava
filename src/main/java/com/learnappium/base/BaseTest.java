package com.learnappium.base;

import com.learnappium.utils.AppiumUtils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {
    public AndroidDriver driver;
    public AppiumUtils utils;

    @BeforeClass
    public void setup() throws MalformedURLException {

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Android");
        options.setPlatformName("Android");
        options.setPlatformVersion("13.0");
        options.setUdid("9LOJ5PC6TO859TD6");
        options.setAutoGrantPermissions(true);
//        options.setNoReset(true);
        options.setAppPackage("com.google.android.dialer");
        options.setAppActivity("com.google.android.dialer.extensions.GoogleDialtactsActivity");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);
        utils = new AppiumUtils(driver);
    }

    @AfterClass
    public void teardown(){
        if(driver != null){
            driver.quit();
        }
    }

    @AfterMethod
    public void takeScreenshotOnFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            utils.takeScreenshot(result.getName());
        }
    }
}
