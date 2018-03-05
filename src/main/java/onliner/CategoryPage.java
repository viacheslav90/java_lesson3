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

    private final static String ALL_CHECKBOXES_CSS = ".schema-filter__checkbox-text";
    private final static String ALL_PRODUCT_URLS_CSS = ".schema-product__title";
    private final static String ALL_PRODUCT_PRICES_URLS_CSS = ".schema-product__price a";
    private final static String ALL_PRODUCT_IMAGES_XPATH = "//*[@id='schema-products']/div/div/div/div/a/img";


    @FindBy(how = How.CSS, using = ALL_CHECKBOXES_CSS)
    private List<WebElement> allCheckboxes;

    @FindBy(how = How.CSS, using = ALL_PRODUCT_URLS_CSS)
    private List<WebElement> allProductURLS;

    @FindBy(how = How.CSS, using = ALL_PRODUCT_PRICES_URLS_CSS)
    private List<WebElement> allProductPricesURLs;

    @FindBy(how = How.XPATH, using = ALL_PRODUCT_IMAGES_XPATH)
    private List<WebElement> allImages;

    public CategoryPage(WebDriver driver) {
        super(driver);
    }


    public void checkFilterCheckbox(String[] checkboxNames) {
        WebDriverWait wait = new WebDriverWait(this.driver, 20);
        wait.until(ExpectedConditions.presenceOfElementLocated(new By.ByCssSelector(ALL_CHECKBOXES_CSS)));
        for (WebElement checkbox : allCheckboxes) {
            for (int i = 0; i < checkboxNames.length; i++) {
                if (checkbox.getText().equalsIgnoreCase(checkboxNames[i])) {
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
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(new By.ByCssSelector(ALL_PRODUCT_PRICES_URLS_CSS)));
        int expectedProductIndex = 0;
        for (int i = 0; i < allProductURLS.size(); i++){
            if(productName.equals(allProductURLS.get(i).getText()))
                return i;
        }
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
        return expectedPrice;
    }

    public List<String> getAllImageSrc() {
        List<String> allSrcImages = new LinkedList<String>();
        for (WebElement image: allImages)
            allSrcImages.add(image.getAttribute("src"));
        return allSrcImages;
    }

}
