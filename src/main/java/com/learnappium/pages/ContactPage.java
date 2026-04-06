package com.learnappium.pages;

import com.learnappium.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v142.webauthn.model.AuthenticatorId;
import org.testng.Assert;

import java.util.List;

public class ContactPage extends BaseTest{
    private By contact_label = By.xpath("//android.widget.TextView[@text = 'Contacts']");
    private By create_new_contact_btn = By.xpath("//android.widget.Button[@text='Create new contact']");
    private By firstName_input = By.xpath("//android.widget.EditText[@text=\"First name\"]");
    private By surName_input = By.xpath("//android.widget.EditText[@text=\"Surname\"]");
    private By companyName_label = By.xpath("//android.widget.TextView[@text='Company']/parent::android.widget.EditText");
    private By phoneNumber_input = By.xpath("//android.widget.TextView[contains(@text, 'Phone')]/parent::android.widget.EditText");
    private By save_btn = By.xpath("//android.widget.TextView[@text=\"Save\"]");

    private By searchContactBar_label = By.xpath("//android.widget.TextView[@text='Search contacts']");
    private By searchContactBar_input = By.xpath("//android.widget.EditText[@text='Search contacts']");
    private By contactInfoWidget_list = By.xpath("//androidx.cardview.widget.CardView");


    public void clickContact(){
        utils.customClick(contact_label);
    }

    public void clickCreateNewButton(){
        utils.customClick(create_new_contact_btn);
    }

    public void enterFirstname(String fname){
        utils.customSendKeys(firstName_input, fname);
    }

    public void enterSurname(String sname){
        utils.customSendKeys(surName_input, sname);
    }

    public void enterCompanyInfo(String info){
        utils.customSendKeys(companyName_label, info);
    }

    public void enterPhoneNumber(String number){
        utils.customSendKeys(phoneNumber_input, number);
    }

    public void clickkSaveButton(){
        utils.customClick(save_btn);
    }

    public void enterNameInSearchContact(String name){
        utils.customClick(searchContactBar_label);
        utils.customSendKeys(searchContactBar_input, name);
    }

    public void verifyNameAndContactOnContactPage(String name, String number){
        boolean matchFound = false;
        List<WebElement> contactWidget = driver.findElements(contactInfoWidget_list);
        for (int i = 0; i < contactWidget.size() ; i++ ){
            WebElement element1 = contactWidget.get(i);
            WebElement nameElement = element1.findElement(By.xpath("./descendant::android.widget.TextView[1]"));
            WebElement numberElement = element1.findElement(By.xpath("./descendant::android.widget.TextView[2]"));
            if(nameElement.getText().contains(name) && numberElement.getText().contains(number)){
                System.out.println("Both name and number found on index: " + (i+1));
                utils.verifyElementText(nameElement, name);
                utils.verifyElementTextContains(numberElement, number);
                matchFound = true;
                break;
            }
        }
        Assert.assertTrue(matchFound, "No contact found with name: '"+name+"' and number: '"+number+"'");
    }
}
