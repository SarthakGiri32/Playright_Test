package org.example.DeepDive;

import com.microsoft.playwright.*;
import org.example.pages.FlipkartHomePage;
import org.example.pages.FlipkartPDPPage;
import org.example.pages.FlipkartSearchResultsPage;
import org.example.resources.ReadConfigFiles;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;

public class LocatorsExamplesTest extends ReadConfigFiles {

    // playwright browser related objects
    static Playwright playwright;
    static Browser browser;
    static BrowserContext context;
    static Page page;

    // page objects
    FlipkartHomePage flipkartHomePage;
    FlipkartSearchResultsPage flipkartSearchResultsPage;
    FlipkartPDPPage flipkartPDPPage;

    // test data
    String searchText;
    String filterName;
    String filterOption;
    String itemName;
    String pinCode;

    @BeforeMethod
    @Parameters({"testName"})
    public void beforeTestMethod(String tName) throws IOException, ParseException {
        // initializing playwright browser and page objects
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
        context = browser.newContext();
        page = context.newPage();
        flipkartHomePage = new FlipkartHomePage(page);
        flipkartSearchResultsPage = new FlipkartSearchResultsPage(page);

        // initializing test data
        JSONObject testDataObject = createJsonArray(tName);
        String className = LocatorsExamplesTest.this.getClass().getName().substring(
                LocatorsExamplesTest.this.getClass().getName().indexOf("Locators"));
        if (testDataObject == null) throw new AssertionError();
        else System.out.println("Test Data found for \"" + className + "\" Test");
        searchText = testDataObject.get("searchText").toString();
        filterName = testDataObject.get("filterName").toString();
        filterOption = testDataObject.get("filterOption").toString();
        itemName = testDataObject.get("itemName").toString();
        pinCode = testDataObject.get("pinCode").toString();
    }

    @Test
    public void mainMethod(){
        flipkartHomePage.navigate();

        // Closing Sign In prompt
        flipkartHomePage.closeSignInPrompt();

        // Clicking on search bar input. Filling the search bar with search text and searching
        flipkartHomePage.startSearchForSearchText(searchText);

        // asserting correct search page load
        flipkartSearchResultsPage.assertingCorrectSearchResultsPage(searchText);

        // Expanding RAM Type filter list
        flipkartSearchResultsPage.expandFilterList(filterName);

        // Selecting "32 GB" Filter option
        flipkartSearchResultsPage.selectingRamCapacityFilter(filterOption);

        // selecting a model of Apple Macbook Pro m1 and Opens a new tab
        Page newPage = flipkartSearchResultsPage
                .selectingSearchPageItem(itemName, context);
        flipkartPDPPage = new FlipkartPDPPage(newPage);

        // asserting correct pdp page load
        flipkartPDPPage.assertingPDPPageLoad(itemName);

        // entering pin code for location verification
        flipkartPDPPage.enteringPinCodeForLocationVerification(pinCode);

        // Buy with exchange option selected
        flipkartPDPPage.clickBuyWithExchangeButton();

        // close Buy with exchange popup
        flipkartPDPPage.clickCloseBuyWithExchangePopup();

        // item added to cart
        flipkartPDPPage.clickOnAddToCartButton(itemName);

    }

    @AfterMethod
    public void afterTestMethod(){
        browser.close();
        playwright.close();
    }
}
