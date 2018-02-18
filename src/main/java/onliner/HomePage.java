package onliner;

import onliner.categories.RefrigeratorPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

    // Refregerator categorie page locator
    private static final By REFRIGERATOR_LINK_LOCATOR = By.xpath("//span[text()='Холодильники']");

    private final WebDriver driver;

    /*
    *  Constructor
     */
    public HomePage(WebDriver driver){
        super(driver);
        this.driver = driver;
        this.openPage(BASE_URL);
    }

    /*
    *  Method open refregerator category
     */
    public RefrigeratorPage openRefrigeratorCategory(){
        WebDriverWait wait = new WebDriverWait(this.driver,10);
        wait.until(ExpectedConditions.elementToBeClickable(REFRIGERATOR_LINK_LOCATOR));
        this.moveAndClick(REFRIGERATOR_LINK_LOCATOR);
        return new RefrigeratorPage(this.driver);
    }

    /*
    *  Method close browser
     */
    public void closeBrowser(){
        this.driver.close();
    }

}
