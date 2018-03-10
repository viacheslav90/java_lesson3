package onliner;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.lang.Thread.sleep;
import static java.util.regex.Pattern.compile;


public class CategoryPage extends BasePage {


    private final static String allCheckboxesCss = ".schema-filter__checkbox-text";
    private final static String allProductUrlsCss = ".schema-product__title";
    private final static String allProductPricesUrlsXpath = "//*[@id='schema-products']/div/div/div/div/div/div[1]/div[1]/a";
    private final static String allProductImagesXpath = "//*[@id='schema-products']/div/div/div/div/a/img";


    @FindBy(how = How.CSS, using = allCheckboxesCss)
    private List<WebElement> allCheckboxes;

    @FindBy(how = How.CSS, using = allProductUrlsCss)
    private List<WebElement> allProductURLS;

    @FindBy(how = How.XPATH, using = allProductPricesUrlsXpath)
    private List<WebElement> allProductPricesURLs;

    @FindBy(how = How.XPATH, using = allProductImagesXpath)
    private List<WebElement> allImages;

    public CategoryPage(WebDriver driver) {
        super(driver);
        logger.info("Init Category page");
    }


    public void checkFilterCheckbox(String[] checkboxNames) {
        WebDriverWait wait = new WebDriverWait(this.driver, 20);
        wait.until(ExpectedConditions.presenceOfElementLocated(new By.ByCssSelector(allCheckboxesCss)));
        for (WebElement checkbox : allCheckboxes) {
            for (String checkboxName: checkboxNames) {
                if (checkbox.getText().equalsIgnoreCase(checkboxName)) {
                    String locationX = String.valueOf((checkbox.getLocation().getX() + 800));
                    String scroll = "window.scrollTo(0 , " + locationX +")";
                    JavascriptExecutor jse = (JavascriptExecutor) driver;
                    jse.executeScript(scroll);
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    checkbox.click();
                    logger.info("Select '" + checkbox.getText() + "' checkbox");
                }
            }
        }
    }

    public List<WebElement> getAllProductURLs(){
        try {
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return allProductURLS;
    }

    public int getProductIndexByName(String productName) {
        WebDriverWait wait = new WebDriverWait(this.driver, 10);
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(new By.ByCssSelector(allProductPricesUrlsXpath)));
        int expectedProductIndex = 0;
        for (int i = 0; i < allProductURLS.size(); i++){
            if(productName.equals(allProductURLS.get(i).getText())) {
                logger.info("Product: " + productName + "has index: " + i);
                return i;
            }
        }
        logger.warning("Product '" + productName + "' name was not found");
        return -1;
    }

    public String getProductPriceByIndex(int index) {
        Pattern pattern = compile("[-]?[0-9]+(,[0-9]+)?");
        String price = allProductPricesURLs.get(index).getText();
        Matcher matcher = pattern.matcher(price);
        String expectedPrice = "";
        StringBuilder sb = new StringBuilder(expectedPrice);
        while (matcher.find()) {
            expectedPrice = matcher.group();
        }
        logger.info("Product with index: " + index + " has price: " + expectedPrice);
        return expectedPrice;
    }

    public float calculateAvaragePrice(){
        Pattern pattern = compile("[-]?[0-9]+(,[0-9]+)?");
        float sumPrices = 0;
        for (WebElement priceUrl: allProductPricesURLs) {
            String price = priceUrl.getText();
            Matcher matcher = pattern.matcher(price);
            String expectedPrice = "";
            StringBuilder sb = new StringBuilder(expectedPrice);
            while (matcher.find()) {
                price = matcher.group();
            }
            String handledPrice = price.replace(',', '.');
            sumPrices += Float.parseFloat(handledPrice);
        }
        float averagePrice = sumPrices/allProductPricesURLs.size();
        float roundedAvgPrice = roundResult(averagePrice, 2);
        logger.info("Average price: " + roundedAvgPrice);
        return roundedAvgPrice;
    }

    public List<String> getAllImageSrc() {
        List<String> allSrcImages = new LinkedList<String>();
        for (WebElement image: allImages)
            allSrcImages.add(image.getAttribute("src"));
        return allSrcImages;
    }

    private float roundResult (float number, int precise) {
        precise = 10^precise;
        number = number*precise;
        int rounded = (int) Math.round(number);
        return (float) rounded/precise;

    }

}
