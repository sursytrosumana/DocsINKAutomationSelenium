package com.orangehrm.tests;

import com.orangehrm.pages.LoginPage;
import com.orangehrm.pages.OTPPage;
import com.orangehrm.utils.EmailOTPReader;
import dataproviders.CSVDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

public class testRegistrationForm extends BaseTest {

    @Test(dataProvider = "registrationData", dataProviderClass = CSVDataProvider.class)
    public void testRegistrationForm(String firstName, String lastName, String email, String phone,
                                     String password, String confirmPassword, String company) throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);

        // 1. Fill form and submit
        loginPage.completeRegistration(firstName, lastName, email, phone, password, confirmPassword, company);


        // 2. Wait for OTP to arrive in email
        String otp = EmailOTPReader.fetchOTP("imap.gmail.com", email, "your-app-password");
        Assert.assertNotNull(otp, "OTP not received via email.");

        // 3. Enter OTP on the web page
        OTPPage otpPage = new OTPPage(driver);  // Create this POM class
        otpPage.enterOTPAndSubmit(otp);

        // 4. Assert successful navigation
        Assert.assertEquals(driver.getCurrentUrl(),
                "https://dev.docs.ink/confirm-registration",
                "Expected redirection after OTP verification.");

        test.pass("✅ OTP verified and registration completed.");
    }

}
