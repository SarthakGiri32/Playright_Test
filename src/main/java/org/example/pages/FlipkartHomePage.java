package org.example.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class FlipkartHomePage {
    private final Page page;
    private final Locator signInPromptCloseButton;
    private final Locator searchBarInput;

    public FlipkartHomePage(Page page){
        this.page = page;
        signInPromptCloseButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("✕"));
        searchBarInput = page.getByTitle("Search for products, brands and more");

    }

    public void navigate(){
        page.navigate("https://www.flipkart.com/");
    }

    public void closeSignInPrompt(){
        signInPromptCloseButton.click();
        System.out.println("Sign In prompt close button clicked...");
    }

    public void startSearchForSearchText(String searchText){
        searchBarInput.click();
        System.out.println("Search bar clicked...");
        searchBarInput.fill(searchText);
        System.out.println("\"" + searchText + "\" search text entered...");
        searchBarInput.press("Enter");
        System.out.println("Search started...");
    }
}
