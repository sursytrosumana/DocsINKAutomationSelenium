package com.orangehrm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(name = "first_name") WebElement firstName;
    @FindBy(name = "last_name") WebElement lastName;
    @FindBy(name = "email") WebElement email;
    @FindBy(name = "phoneNumber") WebElement phone;
    @FindBy(name = "password") WebElement password;
    @FindBy(name = "confirmPassword") WebElement confirmPassword;
    @FindBy(name = "company_name") WebElement company;
    @FindBy(id = "checkbox-tos") WebElement agreeTerms;
    @FindBy(css = "button[type='submit']") WebElement register;

    public void completeRegistration(String fn, String ln, String em, String ph, String pw, String cpw, String comp) throws InterruptedException {
        waitForVisibility(firstName).sendKeys(fn);
        waitForVisibility(lastName).sendKeys(ln);
        waitForVisibility(email).sendKeys(em);
        waitForVisibility(phone).click();
        waitForVisibility(phone).clear();
        waitForVisibility(phone).sendKeys(ph);
        waitForVisibility(password).sendKeys(pw);
        waitForVisibility(confirmPassword).sendKeys(cpw);
        waitForVisibility(company).sendKeys(comp);
        if (isDisplayed(agreeTerms) && agreeTerms.isEnabled() && !agreeTerms.isSelected()) {
            click(agreeTerms);
        }
        Thread.sleep(5000);
        waitForClickability(register).click();

    }
}
