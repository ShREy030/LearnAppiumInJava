package com.automation;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

public class CreateContact {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        URL url = new URL("http://127.0.0.1:4723/");
        String firstName = "Appium";
        String lastName = "Automation";

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Android");
        options.setPlatformName("Android");
        options.setPlatformVersion("13.0");
        options.setUdid("9LOJ5PC6TO859TD6");
//        options.setNoReset(true);
        options.setAppPackage("com.google.android.dialer");
        options.setAppActivity("com.google.android.dialer.extensions.GoogleDialtactsActivity");

        AndroidDriver driver = new AndroidDriver(url, options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        By contactElement = By.xpath("//android.widget.TextView[@text = 'Contacts']");
        By createNewContactElement = By.xpath("//android.widget.Button[@text='Create new contact']");
        By firstNameElement = By.xpath("//android.widget.EditText[@text=\"First name\"]");
        By surNameElement = By.xpath("//android.widget.EditText[@text=\"Surname\"]");
        By companyName = By.xpath("//android.widget.TextView[@text='Company']/parent::android.widget.EditText");
        By phoneNumberElement = By.xpath("//android.widget.EditText[@text=\"+1\"]");
        By saveElement = By.xpath("//android.widget.TextView[@text=\"Save\"]");

        driver.findElement(contactElement).click();
        driver.findElement(createNewContactElement).click();
        driver.findElement(firstNameElement).sendKeys(firstName);
        driver.findElement(surNameElement).sendKeys(lastName);
        driver.findElement(companyName).sendKeys("Upadhyay Company");
        driver.findElement(phoneNumberElement).clear();
        driver.findElement(phoneNumberElement).click();
        driver.findElement(phoneNumberElement).sendKeys("+1 1234567890");
        driver.findElement(saveElement).click();

//      Verify number display when search on search Contact bar

        By searchContactBarTextViewElement = By.xpath("//android.widget.TextView[@text='Search contacts']");
        By searchContactBarEditTextElement = By.xpath("//android.widget.EditText[@text='Search contacts']");

//        By contactInfoWidgetElement =

        By contactInfoWidgetElement = By.xpath("//androidx.cardview.widget.CardView");

        driver.findElement(searchContactBarTextViewElement).click();
        driver.findElement(searchContactBarEditTextElement).sendKeys(firstName + " " + lastName);

        driver.quit();

    }
}
