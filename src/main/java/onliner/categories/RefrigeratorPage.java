package onliner.categories;

import onliner.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.lang.Thread.sleep;
import static java.util.regex.Pattern.compile;
public class RefrigeratorPage extends BasePage{

    // All checkboxes on page locator
    private final static By ALL_CHECKBOXES = By.xpath("//span[@class=\"schema-filter__checkbox-text\"]");

    // List of all available refregerator URLs locator
    private final static By ALL_REFRIGERATORS_URLS = By.cssSelector(".schema-product__title");

    // List of all available prices for refregerators locator
    private final static By ALL_REFRIGERATORS_PRICES_URLS = By.cssSelector(".schema-product__price a");

    private final WebDriver driver;

    /*
    *  Constructor
     */
    public RefrigeratorPage(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    /*
    *  Method make checkbox checked
    *  @param String[] checkboxNames
     */
    public void checkCheckbox(String[] checkboxNames){
        WebDriverWait wait = new WebDriverWait(this.driver, 10);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(ALL_CHECKBOXES));
        List<WebElement> checkboxList = this.driver.findElements(ALL_CHECKBOXES);
        for(int i = 0; i < checkboxList.size(); i++){
            for (int j = 0; j < checkboxNames.length; j++){
                if(checkboxList.get(i).getText().equals(checkboxNames[j])){
                    checkboxList.get(i).click();
                }
            }
        }
    }

    /*
    *  Method return all refregerators URLs
    *  @return List<WebElement>
     */
    public List<WebElement> getAllRefrigeratorsURLs(){
        try {
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this.driver.findElements(ALL_REFRIGERATORS_URLS);
    }

    /*
    *  Method return price for refregerator
    *  @param refregeratorName
    *  @return expectedPrice
     */
    public String getRefrigeratorPrice(String refregeratorName){
        WebDriverWait wait = new WebDriverWait(this.driver, 10);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(ALL_REFRIGERATORS_PRICES_URLS));
        List<WebElement> refrigeratorPricesURLs = this.driver.findElements(ALL_REFRIGERATORS_PRICES_URLS);
        List<WebElement> refrigeratorsURLs = this.getAllRefrigeratorsURLs();
        int expectedRefrigeratorIndex = 0;
        for (int i = 0; i < refrigeratorsURLs.size(); i++){
            if(refregeratorName.equals(refrigeratorsURLs.get(i).getText()))
                expectedRefrigeratorIndex = i;
        }
        Pattern patern = compile("[-]?[0-9]+(,[0-9]+)?");
        String price = refrigeratorPricesURLs.get(expectedRefrigeratorIndex).getText();
        Matcher matcher = patern.matcher(price);
        String expectedPrice = "";
        StringBuilder sb = new StringBuilder(expectedPrice);
        while (matcher.find()) {
            expectedPrice = matcher.group();
        }
        return expectedPrice;
    }

}
