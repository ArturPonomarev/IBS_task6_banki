package banki.tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import banki.framework.driver.DriverManager;
import banki.framework.utils.FrameworkUtils;
import banki.framework.utils.JsonDataProvider;
import banki.framework.utils.MyAllureListener;

@ExtendWith(MyAllureListener.class)
public abstract class BaseTest {

    protected WebDriver driver = DriverManager.getInstance().getDriver();

    @BeforeAll
    public static void oneTimeSetUp() {
        FrameworkUtils.initFramework();
    }

    @BeforeEach
    public void setUp() {
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.navigate().to(JsonDataProvider.testData.baseUrl);
    }

    @AfterEach
    public void tearDown() {

    }

    @AfterAll
    public static void afterAll() {
        FrameworkUtils.closeFramework();
    }
}