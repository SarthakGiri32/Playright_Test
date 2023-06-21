package org.example;

import com.microsoft.playwright.*;

public class EmulatingDevice {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(false));
            BrowserContext context = browser.newContext(new Browser.NewContextOptions()
                    .setDeviceScaleFactor(4.5)
                    .setHasTouch(true)
                    .setIsMobile(true)
                    .setUserAgent("Mozilla/5.0 (Linux; Android 8.0.0; SM-G965U Build/R16NW) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.5790.24 Mobile Safari/537.36")
                    .setViewportSize(320, 658));
            Page page = context.newPage();
            page.navigate("https://playwright.dev/");
        }
    }
}