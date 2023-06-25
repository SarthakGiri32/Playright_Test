package org.example.DeepDive;

import com.microsoft.playwright.*;
import org.example.pages.FlipkartHomePage;
import org.example.pages.FlipkartPDPPage;
import org.example.pages.FlipkartSearchResultsPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LocatorsExamplesTest {

    static Playwright playwright;
    static Browser browser;
    static BrowserContext context;
    static Page page;
    FlipkartHomePage flipkartHomePage;
    FlipkartSearchResultsPage flipkartSearchResultsPage;
    FlipkartPDPPage flipkartPDPPage;

    @BeforeMethod
    public void beforeTestMethod(){
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(2000));
        context = browser.newContext();
        page = context.newPage();
        flipkartHomePage = new FlipkartHomePage(page);
        flipkartSearchResultsPage = new FlipkartSearchResultsPage(page);
    }

    @Test
    public void mainMethod(){
        flipkartHomePage.navigate();

        // Closing Sign In prompt
        flipkartHomePage.closeSignInPrompt();

        // Clicking on search bar input. Filling the search bar with search text and searching
        flipkartHomePage.startSearchForSearchText("Apple Macbook Pro m1");

        // asserting correct search page load
        flipkartSearchResultsPage.assertingCorrectSearchResultsPage("Apple Macbook Pro m1");

        // Expanding RAM Type filter list
        flipkartSearchResultsPage.expandFilterList("RAM Capacity");

        // Selecting "32 GB" Filter option
        flipkartSearchResultsPage.selectingRamCapacityFilter("32 GB");

        // selecting a model of Apple Macbook Pro m1 and Opens a new tab
        Page newPage = flipkartSearchResultsPage
                .selectingSearchPageItem("APPLE 2021 Macbook Pro M1 Max - (32 GB/1 TB SSD/Mac OS Monterey) MK1A3HN/A", context);
        flipkartPDPPage = new FlipkartPDPPage(newPage);

        // asserting correct pdp page load
        flipkartPDPPage.assertingPDPPageLoad("APPLE 2021 Macbook Pro M1 Max - (32 GB/1 TB SSD/Mac OS Monterey) MK1A3HN/A");

        // entering pin code for location verification
        flipkartPDPPage.enteringPinCodeForLocationVerification("831005");

        // Buy with exchange option selected
        flipkartPDPPage.clickBuyWithExchangeButton();

    }

    @AfterMethod
    public void afterTestMethod(){
        browser.close();
        playwright.close();
    }
}
