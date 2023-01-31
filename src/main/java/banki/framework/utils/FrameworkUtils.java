package banki.framework.utils;

import banki.framework.driver.DriverManager;

import java.time.Duration;

public class FrameworkUtils {
    private static final DriverManager driverManager = DriverManager.getInstance();

    public static void initFramework() {

    }

    public static void initDriver() {
        driverManager.getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(JsonDataProvider.configData.pageLoadTimeout));
        driverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(JsonDataProvider.configData.elementWaitingTimeoutImplicit));
        driverManager.getDriver().manage().deleteAllCookies();
        driverManager.getDriver().manage().window().maximize();
    }

    public static void closeDriver() {
        driverManager.quitDriver();
    }

    public static void closeFramework() {
    }
}