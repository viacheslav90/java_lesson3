package onliner.factories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {

    private static WebDriver webDriver = null;

    public static WebDriver getWebDriver(){
        if (webDriver != null) {
            return webDriver;
        } else
            return new FirefoxDriver();
    }

}