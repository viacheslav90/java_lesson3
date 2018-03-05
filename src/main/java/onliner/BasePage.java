package onliner;

import org.openqa.selenium.WebDriver;

public class BasePage {

    protected WebDriver driver;
    private static String BASE_URL = "https://onliner.by";

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected void navigateToHomePage() {
        driver.manage().deleteAllCookies();
        driver.get(BASE_URL);
    }

}