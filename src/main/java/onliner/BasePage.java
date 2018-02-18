package onliner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    // Base URL
    protected static final String BASE_URL = "https://onliner.by/";

    private final WebDriver driver;
    /*
    *  Constructor
    */
    public BasePage (WebDriver driver){
        this.driver = driver;
    }

    /*
    *  Method open page
    *  @param pageURL
     */
    public void openPage(String pageURL){
        this.driver.manage().window().maximize();
        this.driver.get(pageURL);
    }

    /*
    *  Method click to element
    *  @param elementLocation
     */
    public void click(By elementLocation){
        this.driver.findElement(elementLocation).click();
    }

    /*
    *  Method move to element and click
    *  @param elementLocation
     */
    public void moveAndClick(By elementLocation){
        WebElement element = this.driver.findElement(elementLocation);
        Actions actions = new Actions(this.driver);
        actions.moveToElement(element).click().perform();
    }

    /*
    *  Method write text
    *  @param elementLocation, text
     */
    public void writeText(By elementLocation, String text){
        this.driver.findElement(elementLocation).sendKeys(text);
    }

    /*
    *  Method select element from drop-down menu
    *  @param elementLocation, value
     */
    public void selectFromDropDown(By elementLocation, String value){
        WebElement selectElement = this.driver.findElement(elementLocation);
        Select select = new Select(selectElement);
        select.selectByValue(value);
    }

    /*
     *  Method close browser
     */
    public void closeBrowser(){
        this.driver.close();
    }
}
