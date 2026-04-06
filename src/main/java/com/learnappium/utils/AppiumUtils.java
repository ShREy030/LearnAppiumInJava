package com.learnappium.utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;


public class AppiumUtils {
    AndroidDriver driver;
    WebDriverWait wait;

    // constructor - receive driver from BaseTest
    public AppiumUtils(AndroidDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Custom SendKeys: Wait -> clear -> click -> type --
    public void customSendKeys(By locator, String text){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.click();
        element.sendKeys(text);
        try{
            if(driver.isKeyboardShown()){
                driver.pressKey(new KeyEvent(AndroidKey.BACK));
                System.out.println("Keyboard was open - pressed backspace");
            }else{
                System.out.println("Keyboard is closed - no action");
            }
        }catch (Exception e){
            System.out.println("Keyboard check failed, trying fallback...");
            try{
                driver.hideKeyboard();
                driver.pressKey(new KeyEvent(AndroidKey.BACK));
            }catch (Exception ex){
                System.out.println("Fallback failed - keyboard might already be closed");
            }
        }
    }

    // Custom click: wait -> click --
    public void customClick(By locator){
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    // wait for the element to be visible
    public WebElement waitForElement(By locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // get string safely
    public String getText(By locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    // check if element is displayed
    public boolean isDisplayed(By locator){
        try{
            return driver.findElement(locator).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }

    public void verifyElementText(WebElement element, String expectedText){
        String actualText = element.getText().trim();
        Assert.assertEquals(actualText, expectedText, "Text mismatch! Expected: ["+expectedText+"], Actual Text: ["+actualText+"]");
    }

    public void verifyElementTextContains(WebElement element, String expectedText) {
        String actualText = element.getText().trim();
        Assert.assertTrue(actualText.contains(expectedText),
                "Text does not contain expected! Expected: [" + expectedText + "], Actual: [" + actualText + "]");
    }

    public void takeScreenshot(String testName){
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String destPath = "screenshots/" + testName + "_" + timestamp + ".png";
        try {
            FileUtils.copyFile(srcFile, new File(destPath));
            System.out.println("Screenshot saved → " + destPath);
        } catch (IOException e) {
            System.out.println("Screenshot failed: " + e.getMessage());
        }
    }


}