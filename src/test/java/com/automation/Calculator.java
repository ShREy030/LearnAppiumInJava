package com.automation;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URL;

public class Calculator {
    public static void main(String[] args) throws MalformedURLException {

        URL url = new URL("http://127.0.0.1:4723/");

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Android");
        options.setPlatformName("Android");
        options.setPlatformVersion("13.0");
        options.setUdid("9LOJ5PC6TO859TD6");
//        options.setNoReset(true);
        options.setAppPackage("com.oneplus.calculator");
        options.setAppActivity("com.android.calculator2.Calculator");

        AndroidDriver driver = new AndroidDriver(url, options);
        WebElement one = driver.findElement(By.id("com.oneplus.calculator:id/digit_1"));
        WebElement five = driver.findElement(By.id("com.oneplus.calculator:id/digit_5"));
        WebElement seven = driver.findElement(By.id("com.oneplus.calculator:id/digit_7"));
        WebElement nine = driver.findElement(By.id("com.oneplus.calculator:id/digit_9"));

        WebElement plusSymbol = driver.findElement(By.id("com.oneplus.calculator:id/img_op_add"));
        WebElement equalSymbol = driver.findElement(By.id("com.oneplus.calculator:id/img_eq"));

        one.click();
        five.click();
        nine.click();
        plusSymbol.click();
        seven.click();
        equalSymbol.click();

        String result = driver.findElement(By.id("com.oneplus.calculator:id/result")).getText();
        System.out.println(result);

        driver.quit();



    }
}
