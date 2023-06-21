package org.example;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;
import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class RecordingTraceTest {

    static Playwright playwright;
    static Browser browser;
    static BrowserContext context;
    static Page page;

    @Test
    public void mainMethod(){

        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false).setSlowMo(1000));
        context = browser.newContext();

        // Start tracing before creating / navigating a page.
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));

        page = context.newPage();
        page.navigate("https://en.wikipedia.org/wiki/Main_Page");

        page.getByPlaceholder("Search Wikipedia").click();
        page.getByPlaceholder("Search Wikipedia").fill("Battle of the bulge");
        page.getByPlaceholder("Search Wikipedia").press("Enter");
        page.navigate("https://en.wikipedia.org/wiki/Peace_treaty");
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Peace treaty under the United Nations")).click();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Hittite Empire")).click();
        assertThat(page).hasURL("https://en.wikipedia.org/wiki/Hittites");

        // Stop tracing and export it into a zip archive.
        context.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get("trace.zip")));

        browser.close();
    }
}
