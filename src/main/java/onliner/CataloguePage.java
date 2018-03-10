package onliner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CataloguePage extends BasePage {


    private static final String electronicsBtnXpath = "//li[contains(@class, 'catalog-navigation-classifier__item') " +
            "and @data-id='1']";
    private static final String mobilePhonesXpath = "//a[@href='https://catalog.onliner.by/mobile' " +
            "and contains(@class, 'catalog-bar__link catalog-bar__link_strong')]";


    @FindBy(how = How.XPATH, using = electronicsBtnXpath)
    private WebElement electronicsBtn;

    @FindBy(how = How.XPATH, using = mobilePhonesXpath)
    private WebElement mobilePhonesBtn;


    public CataloguePage(WebDriver driver) {
        super(driver);
        logger.info("Init Catalogue page");
    }


    public void clickElectronicsBtn() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(electronicsBtn));
        electronicsBtn.click();
        logger.info("Click 'Electronics' button");
    }

    public CategoryPage clickMobPhonesBtn() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(mobilePhonesBtn));
        mobilePhonesBtn.click();
        logger.info("Click 'Mobile phones' button");
        return new CategoryPage(driver);
    }

}
