package org.example.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class FlipkartPDPPage {

    private final Page page;

    public FlipkartPDPPage(Page page){
        this.page = page;
    }

    public void assertingPDPPageLoad(String searchPageItem){
        Locator pdpPageMainHeader = page.locator("span").filter(new Locator.FilterOptions().setHasText(searchPageItem));
        assertThat(pdpPageMainHeader).isVisible();
        System.out.println("PDP Page for \"" + searchPageItem + "\" Item loaded");
    }
}
