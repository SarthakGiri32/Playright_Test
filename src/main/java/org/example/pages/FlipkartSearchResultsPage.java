package org.example.pages;

import com.microsoft.playwright.BrowserContext;
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

    public void expandFilterList(String filterName){
        Locator filterListToggle = page.getByText(filterName, new Page.GetByTextOptions().setExact(true));
        filterListToggle.click();
        System.out.println("\"" + filterName + "\" Filter List expanded...");
    }

    public void selectingRamCapacityFilter(String ramCapacity){
        Locator ramCapacityFilterCheckbox = page.getByText(ramCapacity, new Page.GetByTextOptions().setExact(true));
        ramCapacityFilterCheckbox.click();
        System.out.println("\"" + ramCapacity + "\" RAM capacity filter selected...");
        Locator ramCapacityFilterApplied = page.locator("(//div[text()='32 GB'])[1]");
        assertThat(ramCapacityFilterApplied).isVisible();
        System.out.println("\"" + ramCapacity + "\" RAM capacity filter selection successful...");
    }

    public Page selectingSearchPageItem(String searchPageItem, BrowserContext context){
        Locator searchPageItemElement = page.getByText(searchPageItem);
        Page newPage = context.waitForPage(searchPageItemElement::click);
        System.out.println("\"" + searchPageItem + "\" search item selected...");
        newPage.waitForLoadState();
        System.out.println("New Page title: " + newPage.title());
        return newPage;
    }



}
