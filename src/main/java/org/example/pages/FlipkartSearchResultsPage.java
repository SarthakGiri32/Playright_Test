package org.example.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class FlipkartSearchResultsPage {
    private final Page page;
    private final Locator searchResultsPageSearchText;

    public FlipkartSearchResultsPage(Page page){
        this.page = page;
        searchResultsPageSearchText = page.locator("//span[@class='_10Ermr']/span");
    }

    public void assertingCorrectSearchResultsPage(String searchText){
        assertThat(searchResultsPageSearchText).hasText(searchText);
        System.out.println("Search Page loaded...");
    }

    public void selectingSearchPageItem(String searchPageItem){
        Locator searchPageItemElement = page.getByText(searchPageItem);
        searchPageItemElement.click();
        System.out.println("\"" + searchPageItem + "\" search item selected...");
    }

}
