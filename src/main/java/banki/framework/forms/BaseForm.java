package banki.framework.forms;

import banki.framework.driver.DriverManager;
import banki.framework.utils.JsonDataProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BaseForm {

    protected WebDriver driver = DriverManager.getInstance().getDriver();
    protected WebDriverWait wait = new WebDriverWait(DriverManager.getInstance().getDriver(),
            Duration.ofSeconds(JsonDataProvider.configData.elementWaitingTimeoutExplicit),
            Duration.ofMillis(JsonDataProvider.configData.explicitPollingInterval));
    public BaseForm() {
        PageFactory.initElements(driver, this);
    }

    public abstract boolean isPageOpen();
}
