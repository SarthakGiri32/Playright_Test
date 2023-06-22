package org.example.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class FlipkartSearchResultsPage {
    private final Page page;

    public FlipkartSearchResultsPage(Page page){
        this.page = page;
    }

    public void assertingCorrectSearchResultsPage(String searchText){
        Locator searchResultsPageSearchText = page.getByText(searchText, new Page.GetByTextOptions().setExact(true));
        assertThat(searchResultsPageSearchText).isVisible();
        System.out.println("Search Page loaded...");
    }

    public void selectingSearchPageItem(String searchPageItem){
        Locator searchPageItemElement = page.getByText(searchPageItem);
        searchPageItemElement.click();
        System.out.println("\"" + searchPageItem + "\" search item selected...");
    }

}
