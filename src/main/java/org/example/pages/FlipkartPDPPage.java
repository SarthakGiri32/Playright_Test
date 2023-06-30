package org.example.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class FlipkartPDPPage {

    private final Page page;
    private final Locator buyWithExchange;
    private final Locator pinCodeInput;
    private final Locator checkPinCode;
    private final Locator closeBuyWithExchangePopup;
    private final Locator acceptCancelExchangeBenefitButton;
    private final Locator addToCartButton;

    public FlipkartPDPPage(Page page){
        this.page = page;
        buyWithExchange = page.locator("label").filter(new Locator.FilterOptions().setHasText("Buy with Exchange"));
        pinCodeInput = page.getByPlaceholder("Enter Delivery Pincode");
        checkPinCode = page.locator("span").filter(new Locator.FilterOptions().setHasText("Check"));
        closeBuyWithExchangePopup = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("✕"));
        acceptCancelExchangeBenefitButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Yes, cancel"));
        addToCartButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add to cart"));
    }

    public void assertingPDPPageLoad(String searchPageItem){
        Locator pdpPageMainHeader = page.locator("span").filter(new Locator.FilterOptions().setHasText(searchPageItem));
        assertThat(pdpPageMainHeader).isVisible();
        System.out.println("PDP Page for \"" + searchPageItem + "\" Item loaded");
    }

    public void enteringPinCodeForLocationVerification(String pinCode){
        pinCodeInput.fill(pinCode);
        assertThat(pinCodeInput).hasValue(pinCode);
        System.out.println("Pin Code entered...");
        checkPinCode.click();
        System.out.println("Pin Code checking...");
        assertThat(buyWithExchange).isVisible();
        assertThat(buyWithExchange).isEnabled();
        System.out.println("Pin Code verification successful...");
    }

    public void clickBuyWithExchangeButton(){
        buyWithExchange.click();
        System.out.println("Buy with exchange option selected...");
    }

    public void clickCloseBuyWithExchangePopup(){
        closeBuyWithExchangePopup.click();
        System.out.println("Buy with Exchange popup closed...");
        acceptCancelExchangeBenefitButton.click();
        System.out.println("Exchange benefit value inclusion cancelled...");
    }

    public void clickOnAddToCartButton(String searchPageItem){
        addToCartButton.click();
        System.out.println("\"" + searchPageItem + "\" item added to cart...");
    }
}
