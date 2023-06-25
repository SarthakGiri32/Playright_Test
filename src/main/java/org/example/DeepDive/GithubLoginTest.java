package org.example.DeepDive;

import com.microsoft.playwright.*;
import org.example.pages.GithubLoginPage;
import org.example.resources.ReadConfigFiles;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GithubLoginTest extends ReadConfigFiles {

    static Playwright playwright;
    static Browser browser;
    static BrowserContext context;
    static Page page;
    GithubLoginPage githubLoginPage;

    @BeforeMethod
    public void beforeTestMethod(){
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(2000));
        context = browser.newContext();
        page = context.newPage();
        githubLoginPage = new GithubLoginPage(page);
    }

    @Test
    public void mainMethod(){
        githubLoginPage.navigate();

        // logging into Github
        githubLoginPage.signIntoGithub("username", "password"); // won't work
    }

    @AfterMethod
    public void afterTestMethod(){
        browser.close();
        playwright.close();
    }

}
