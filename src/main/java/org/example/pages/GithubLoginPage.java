package org.example.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class GithubLoginPage {

    private final Page page;
    private final Locator userNameField;
    private final Locator passwordField;
    private final Locator signInButton;

    public GithubLoginPage(Page page){
        this.page = page;
        userNameField = page.getByLabel("Username or email address");
        passwordField = page.getByLabel("Password");
        signInButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign in"));
    }

    public void navigate(){
        page.navigate("https://github.com/login");
    }

    public void signIntoGithub(String userName, String password){
        userNameField.fill(userName);
        passwordField.fill(password);
        signInButton.click();
    }

}
