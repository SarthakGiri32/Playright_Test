package org.example;

import com.microsoft.playwright.*;
import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class WebNavTestNgTest {
    //shared between as tests in this class
    static Playwright playwright;
    static Browser browser;

    //New instance for each test method
    BrowserContext context;
    Page page;

    @BeforeTest
    public void launchBrowser(){
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(3000));
        System.out.println("Browser launched");
    }

    @AfterTest
    public void closeBrowser() {
        playwright.close();
        System.out.println("Browser closed");
    }

    @BeforeMethod
    public void createNewContextAndPage(){
        context = browser.newContext();
        page = context.newPage();
        System.out.println("New Browser context launched");
    }

    @AfterMethod
    public void closeCurrentContext(){
        context.close();
        System.out.println("Current Browser context closed");
    }

    @Test
    public void shouldClickButton(){
        page.navigate("data:text/html,<script>var result;</script><button onclick='result=\"Clicked\"'>Go</button>");
        page.locator("button").click();
        assertEquals("Clicked", page.evaluate("result"));
        System.out.println("Button clicked");
    }

    @Test(priority = 1)
    public void shouldCheckTheBox(){
        page.setContent("<input id='checkbox' type='checkbox'></input>");
        page.locator("input").check();
        assertTrue((Boolean) page.evaluate("() => window['checkbox'].checked"));
        System.out.println("Checkbox checked");
    }

    @Test(priority = 2)
    public void shouldSearchWiki(){
        page.navigate("https://en.wikipedia.org/");
        System.out.println("Wikipedia website loaded");
        page.locator("input[name=\"search\"]").click();
        System.out.println("Search bar clicked");
        page.locator("input[name=\"search\"]").fill("playwright");
        System.out.println("\"Playwright\" entered in search bar");
        page.locator("input[name=\"search\"]").press("Enter");
        System.out.println("Pressed enter on keyboard");
        assertEquals("https://en.wikipedia.org/wiki/Playwright", page.url());
        System.out.println("\"Playwright\" Wikipedia page loaded");
    }
}
