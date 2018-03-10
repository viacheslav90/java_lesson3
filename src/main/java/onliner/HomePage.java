package onliner;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HomePage extends BasePage {


    private static final String catalogueBtnXpath = "//a[@href='https://catalog.onliner.by/']/span";


    @FindBy(how = How.XPATH, using = catalogueBtnXpath)
    private WebElement catalogueBtn;


    public HomePage (WebDriver driver){
        super(driver);
        logger.info("Init Home page");
    }

    public CataloguePage openCatalogue () {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(catalogueBtn));
        catalogueBtn.click();
        logger.info("Click 'Catalogue' button");
        return new CataloguePage(driver);
    }

}
