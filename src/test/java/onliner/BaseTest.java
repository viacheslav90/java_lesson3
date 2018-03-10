package onliner;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.logging.Logger;


public class BaseTest {


    private static String baseUrl = "https://onliner.by";
    protected WebDriver driver;
    protected Logger logger;


    @BeforeSuite(alwaysRun = true)
    protected void beforeSuite(){
        Logger.getAnonymousLogger();
    }

    @BeforeMethod(alwaysRun = true)
    protected void beforeTest() {
        driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.get(baseUrl);
    }

    @AfterMethod(alwaysRun = true)
    protected void afterMethod(ITestResult result, Method method) {
        if (!result.isSuccess()) {
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(scrFile, new File(String.format("screenshots\\%s\\%s.png", this.getClass().toString(), method.getName())));
                logger.info("Save screenshot");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        driver.quit();
    }

    @AfterSuite(alwaysRun = true)
    protected void afterSuite(){
        if (driver != null)
            driver.quit();
    }
}
