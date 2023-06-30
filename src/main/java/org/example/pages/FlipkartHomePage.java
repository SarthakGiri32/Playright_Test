package org.example.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class FlipkartHomePage {
    private final Page page;
    private final Locator signInPromptCloseButton;
    private final Locator searchBarInput;

    public FlipkartHomePage(Page page){
        this.page = page;
        signInPromptCloseButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("✕"));
        searchBarInput = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Search for products, brands and more"));
    }

    public void navigate(){
        page.navigate("https://www.flipkart.com/");
    }

    public void closeSignInPrompt(){
        try {
            signInPromptCloseButton.waitFor(new Locator.WaitForOptions().setTimeout(3000));
            signInPromptCloseButton.click();
            System.out.println("Sign In prompt close button clicked...");
        } catch (Exception e) {
            System.out.println("Sign In prompt not found, can continue with flow...");
        }
    }

    public void startSearchForSearchText(String searchText){
        searchBarInput.click();
        System.out.println("Search bar clicked...");
        searchBarInput.fill(searchText);
        assertThat(searchBarInput).hasValue(searchText);
        System.out.println("\"" + searchText + "\" search text entered...");
        searchBarInput.press("Enter");
        System.out.println("Search started...");
    }
}
