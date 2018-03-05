package onliner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CataloguePage extends BasePage {


    private static final String ELECTRONICS_BTN_XPATH = "//li[contains(@class, \"catalog-navigation-classifier__item\") and @data-id=\"1\"]";
    private static final String MOBILE_PHONES_XPATH = "//a[@href='https://catalog.onliner.by/mobile' " +
            "and contains(@class, 'catalog-bar__link catalog-bar__link_strong')]";


    @FindBy(how = How.XPATH, using = ELECTRONICS_BTN_XPATH)
    private WebElement electronicsBtn;

    @FindBy(how = How.XPATH, using = MOBILE_PHONES_XPATH)
    private WebElement mobilePhonesBtn;


    public CataloguePage(WebDriver driver) {
        super(driver);
    }

    public void clickElectronicsBtn() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(electronicsBtn));
        electronicsBtn.click();
    }

    public void clickMobPhonesBtn() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(mobilePhonesBtn));
        mobilePhonesBtn.click();
    }

}
