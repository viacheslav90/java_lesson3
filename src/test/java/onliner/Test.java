package onliner;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;

public class Test {

    private WebDriver driver;
    private HomePage homePage;
    private CataloguePage cataloguePage;
    private CategoryPage categoryPage;


    @BeforeTest
    private void beforeTest() {
        driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1920, 1080));
    }

    @BeforeClass
    private void beforeClass() {
        homePage = PageFactory.initElements(driver, HomePage.class);
        cataloguePage = PageFactory.initElements(driver, CataloguePage.class);
        categoryPage = PageFactory.initElements(driver, CategoryPage.class);
    }

    @AfterClass
    private void closeDriver() {
        driver.quit();
    }

    @BeforeMethod
    private void openHomePage() {
        homePage.navigateToHomePage();
    }


    @org.testng.annotations.Test
    public void  getThirdWebElementImage() {
        homePage.openCatalogue();
        cataloguePage.clickMobPhonesBtn();
        String srcUrl = categoryPage.getAllImageSrc().get(2);
        System.out.println(srcUrl);
    }

    @org.testng.annotations.Test
    public void displayFirstSmartphonePrice() {
        homePage.openCatalogue();
        cataloguePage.clickMobPhonesBtn();
        String price = categoryPage.getProductPriceByIndex(1);
        System.out.println(price);
    }

    @org.testng.annotations.Test
    public void selectFilterCheckbox() {
        homePage.openCatalogue();
        cataloguePage.clickMobPhonesBtn();
        categoryPage.checkFilterCheckbox(new String []{"5 - 5.5\""});
    }
}
