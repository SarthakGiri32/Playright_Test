package org.example;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CodegenTestRecordingTest {

    static Playwright playwright;
    static Browser browser;
    static BrowserContext context;
    static Page page;


    @BeforeMethod
    public void beforeTestMethod(){
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false).setSlowMo(1000));
        context = browser.newContext();
        page = context.newPage();
    }

    @Test
    public void mainMethod(){
            page.navigate("https://en.wikipedia.org/wiki/Main_Page");
            page.getByPlaceholder("Search Wikipedia").click();
            page.getByPlaceholder("Search Wikipedia").fill("Battle of the bulge");
            page.getByPlaceholder("Search Wikipedia").press("Enter");
            page.navigate("https://en.wikipedia.org/wiki/Peace_treaty");
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Peace treaty under the United Nations")).click();
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Hittite Empire")).click();
            assertThat(page).hasURL("https://en.wikipedia.org/wiki/Hittites");
    }

    @AfterMethod
    public void afterTestMethod(){
        browser.close();
    }
}
