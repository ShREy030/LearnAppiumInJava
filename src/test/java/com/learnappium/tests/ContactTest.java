package com.learnappium.tests;

import com.learnappium.pages.ContactPage;
import org.testng.annotations.Test;

public class ContactTest extends ContactPage {
    public String fname= "Appium";
    public String sname = "Automation";
    public String cname = "Upadhyay Family";
    public String number = "1 2345678902";
    @Test
    public void testValidContact() throws InterruptedException {
        clickContact();
        clickCreateNewButton();
        enterFirstname(fname);
        enterSurname(sname);
        enterCompanyInfo(cname);
        enterPhoneNumber(number);
        clickkSaveButton();
        enterNameInSearchContact(fname + " " + sname);
        verifyNameAndContactOnContactPage("sdfsdfsdfsd", "123123123213");
    }
}
