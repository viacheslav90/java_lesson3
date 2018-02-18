package onliner;

import onliner.categories.RefrigeratorPage;
import onliner.factories.WebDriverFactory;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import java.util.List;

public class Test extends Assert{

    private HomePage homePage;
    private RefrigeratorPage refrigeratorPage;

    @BeforeTest
    private void setUpBeforeTest(){
        this.homePage = new HomePage(WebDriverFactory.getWebDriver());
    }

    @AfterTest
    private void tearDown(){
        this.refrigeratorPage.closeBrowser();
    }


    /*
    * Checking of filtering refregerators and correct price
     */
    @org.testng.annotations.Test
    private void CorrectFilteringAndRefrigeratorPriceTest(){
        refrigeratorPage = this.homePage.openRefrigeratorCategory();
        String[] checkboxName = {"ATLANT"};
        refrigeratorPage.checkCheckbox(checkboxName);
        List<WebElement> refrigeratorURLs = refrigeratorPage.getAllRefrigeratorsURLs();
        for (int i = 0; i < refrigeratorURLs.size(); i++){
            assertTrue(refrigeratorURLs.get(i).getText().contains("ATLANT"));
        }
        String price = refrigeratorPage.getRefrigeratorPrice("Холодильник ATLANT ХМ 6325-101");
        assertEquals(price, "659,00");
    }
}
