package ru.netology.amazon.utils;

import com.microsoft.playwright.*;
import java.nio.file.Paths;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadManager {
    private static final String SCREENSHOT_DIR = "build/screenshots/";
    private static final int THREAD_POOL_SIZE = 3;

    public static void runTestsInParallel(String baseUrl) {
        String[] browsers = getBrowsersFromSystemProperty();
        ExecutorService executor = Executors.newFixedThreadPool(
                Math.min(browsers.length, getThreadPoolSize())
        );

        final CountDownLatch latch = new CountDownLatch(browsers.length);

        for (String browserName : browsers) {
            executor.submit(() -> {
                try {
                    runBrowserTest(browserName, baseUrl);
                } finally {
                    latch.countDown();
                }
            });
        }

        try {
            latch.await(2, TimeUnit.MINUTES);
            executor.shutdown();
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static void runBrowserTest(String browserName, String baseUrl) {
        try (Playwright playwright = Playwright.create()) {
            BrowserType browserType = getBrowserType(playwright, browserName);

            BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions()
                    .setHeadless(isHeadless())
                    .setSlowMo(getSlowMo());

            Browser browser = browserType.launch(launchOptions);
            BrowserContext context = browser.newContext();

            Page page = context.newPage();
            page.navigate(baseUrl);

            // Логирование информации
            System.out.println(String.format(
                    "[%s] Browser: %s, Title: %s, Thread: %s",
                    Thread.currentThread().getName(),
                    browserName,
                    page.title(),
                    Thread.currentThread().getId()
            ));

            // Создание скриншота
            Paths.get(SCREENSHOT_DIR).toFile().mkdirs();
            String screenshotPath = String.format(
                    "%s%s-%s-%d.png",
                    SCREENSHOT_DIR,
                    browserName,
                    Thread.currentThread().getName(),
                    System.currentTimeMillis()
            );

            page.screenshot(new Page.ScreenshotOptions()
                    .setPath(Paths.get(screenshotPath))
                    .setFullPage(true));

            context.close();
            browser.close();

        } catch (Exception e) {
            System.err.println("Error in " + browserName + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static BrowserType getBrowserType(Playwright playwright, String browserName) {
        switch (browserName.toLowerCase()) {
            case "chromium": return playwright.chromium();
            case "webkit": return playwright.webkit();
            case "firefox": return playwright.firefox();
            default: throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }
    }

    private static String[] getBrowsersFromSystemProperty() {
        String browserProp = System.getProperty("browser", "chromium");
        if ("all".equalsIgnoreCase(browserProp)) {
            return new String[]{"chromium", "firefox", "webkit"};
        }
        return browserProp.split(",");
    }

    private static int getThreadPoolSize() {
        return Integer.parseInt(System.getProperty("threads", String.valueOf(THREAD_POOL_SIZE)));
    }

    private static boolean isHeadless() {
        return Boolean.parseBoolean(System.getProperty("headless", "true"));
    }

    private static int getSlowMo() {
        return Integer.parseInt(System.getProperty("slowMo", "0"));
    }

    // Метод для запуска из тестов
    public static void runAmazonTests() {
        String baseUrl = System.getProperty("baseUrl", "https://www.amazon.com");
        runTestsInParallel(baseUrl);
    }
}