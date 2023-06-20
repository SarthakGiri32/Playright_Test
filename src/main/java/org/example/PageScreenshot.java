package org.example;

import com.microsoft.playwright.*;

import java.nio.file.Paths;

public class PageScreenshot {
    public static void main(String[] args){
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(5000));
            Page page = browser.newPage();
            page.navigate("https://google.com/");
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("Screenshots/MainPageScreenshot.png")));
            System.out.println("Screenshot taken");
        }
    }
}
