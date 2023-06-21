package org.example.DeepDive;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LocatorsExamplesTest {

    @Test
    public void mainMethod(){
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false).setSlowMo(1000));
            BrowserContext context = browser.newContext();
            Page page = context.newPage();
            page.navigate("https://www.flipkart.com/");

            // Closing Sign In prompt
            Locator signInPromptCloseButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("✕"));
            System.out.println("Sign In prompt close button found...");
            signInPromptCloseButton.click();
            System.out.println("Sign In prompt close button clicked...");

            // Clicking on search bar input
            Locator searchBarInput = page.getByTitle("Search for products, brands and more");
            System.out.println("Search bar found...");
            searchBarInput.click();
            System.out.println("Search bar clicked...");

            // Filling the search bar with search text and searching
            searchBarInput.fill("Apple Macbook Pro m1");
            System.out.println("\"Apple Macbook Pro m1\" search text entered...");
            searchBarInput.press("Enter");
            System.out.println("Search started...");

            // asserting correct search page load
            assertThat(page.locator("//span[@class='_10Ermr']/span")).hasText("Apple Macbook Pro m1");
            System.out.println("Search Page loaded...");

            // selecting a model of Apple Macbook Pro m1
            Locator appleMacbookMaxModel = page.getByText("APPLE 2021 Macbook Pro M1 Max - (32 GB/1 TB SSD/Mac OS Monterey) MK1A3HN/A");
            System.out.println("Macbook model found...");
            appleMacbookMaxModel.click();
            System.out.println("Macbook model clicked...");
        }
    }
}
