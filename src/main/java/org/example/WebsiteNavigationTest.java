package org.example;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class WebsiteNavigationTest {

    @Test
    public static void mainMethod(){
        try (Playwright playwright = Playwright.create()){
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(3000));
            BrowserContext context = browser.newContext();
            Page page = context.newPage();
            page.navigate("https://playwright.dev/");

            //Expect a title to contain a substring
            assertThat(page).hasTitle(Pattern.compile("Playwright"));

            //create a locator
            Locator getStartedLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Get Started"));

            //Expect an attribute "to be strictly equal" to the value.
            assertThat(getStartedLink).hasAttribute("href", "/docs/intro");

            //Click the get started link.
            getStartedLink.click();

            //Expect the URL to contain intro.
            assertThat(page).hasURL(Pattern.compile(".*intro"));
        }
    }
}
