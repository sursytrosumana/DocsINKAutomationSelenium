package com.orangehrm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OTPPage extends BasePage {

    public OTPPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(name = "otp")
    WebElement otpInput;

    @FindBy(css = "button[type='submit']")
    WebElement verifyButton;

    public void enterOTPAndSubmit(String otp) {
        type(otpInput, otp);
        click(verifyButton);
    }
}
