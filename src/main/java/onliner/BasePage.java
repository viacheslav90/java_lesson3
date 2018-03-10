package onliner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import java.util.logging.Logger;


public class BasePage {


    protected WebDriver driver;
    protected Logger logger;


    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.logger = Logger.getAnonymousLogger();
        PageFactory.initElements(driver, this);
    }

}