package com.orangehrm.tests;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.orangehrm.utils.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.lang.reflect.Method;


import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.apache.commons.io.FileUtils.copyFile;

public class BaseTest {
    protected WebDriver driver;
    private ExtentReports extent;
    protected ExtentTest test;
    protected ConfigReader config;

    @BeforeSuite
    public void initReport() {
        extent = new ExtentReports();
        config = new ConfigReader();

        ExtentSparkReporter reporter = new ExtentSparkReporter("test-output/ExtentReport.html");
        reporter.config().setDocumentTitle("DocsInk Report");
        reporter.config().setReportName("Registration Results");

        extent.attachReporter(reporter);
        extent.setSystemInfo("Environment", config.getProperty("env", "QA"));
    }

    @BeforeMethod
    public void launch(Method method) {
        test = extent.createTest(method.getName());

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions()
                .addArguments("--no-sandbox", "--disable-dev-shm-usage", "--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(config.getProperty("app.url"));
    }

    @AfterMethod
    public void cleanup(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.fail("❌ " + result.getThrowable());
            test.addScreenCaptureFromPath(captureScreenshot(result.getName()));
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("✅ Test passed");
        } else {
            test.skip("⚠️ Test skipped: " + result.getThrowable());
        }

        if (driver != null) driver.quit();
    }

    @AfterSuite
    public void flushReport() {
        extent.flush();
    }

    private String captureScreenshot(String testName) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            File dest = new File("test-output/screenshots/" + testName + "_" + timestamp + ".png");
            copyFile(src, dest);
            return dest.getPath();
        } catch (IOException e) {
            test.warning("Screenshot capture failed: " + e.getMessage());
            return null;
        }
    }
}
