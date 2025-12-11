import com.microsoft.playwright.*;

public class SimpleTest {
    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            System.out.println("Playwright version: " + playwright.version());
            Browser browser = playwright.chromium().launch();
            System.out.println("Browser type: " + browser.browserType().name());
            browser.close();
        }
    }
}
