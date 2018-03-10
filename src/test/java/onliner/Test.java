package onliner;

import io.restassured.RestAssured;
import org.testng.annotations.DataProvider;


public class Test extends BaseTest{


    private HomePage homePage;
    private CataloguePage cataloguePage;
    private CategoryPage categoryPage;


    @DataProvider(name = "CheckboxNameDataProvider")
    private static Object[][] checkboxName(){
        return new Object[][]{{"Nokia"}, {"5 - 5.5\""}, {"2017"}, {"AMOLED"}};
    }


    @org.testng.annotations.Test(description = "Check 200 status code for GET request to 3-rd image")
    private void  checkGetRequestToThirdWebElementImage() {
        homePage = new HomePage(driver);
        cataloguePage = homePage.openCatalogue();
        categoryPage = cataloguePage.clickMobPhonesBtn();
        String srcUrl = categoryPage.getAllImageSrc().get(2);
        System.out.println(srcUrl);
        RestAssured.get(srcUrl).then().assertThat().statusCode(200);
    }

    @org.testng.annotations.Test(description = "Check first smartphone price")
    private void displayFirstSmartphonePrice() {
        homePage = new HomePage(driver);
        cataloguePage = homePage.openCatalogue();
        categoryPage = cataloguePage.clickMobPhonesBtn();
        String price = categoryPage.getProductPriceByIndex(0);
        System.out.println(price);
    }

    @org.testng.annotations.Test(description = "Select filter checkbox", dataProvider = "CheckboxNameDataProvider")
    private void selectFilterCheckbox(String checkbox) {
        homePage = new HomePage(driver);
        cataloguePage = homePage.openCatalogue();
        categoryPage = cataloguePage.clickMobPhonesBtn();
        categoryPage.checkFilterCheckbox(new String []{checkbox});
    }

    @org.testng.annotations.Test(description = "Check average price")
    private void displayAvaragePrice(){
        homePage = new HomePage(driver);
        cataloguePage = homePage.openCatalogue();
        categoryPage = cataloguePage.clickMobPhonesBtn();
        float averagePrice = categoryPage.calculateAvaragePrice();
        System.out.println("Average price is: " + averagePrice);
    }
}
