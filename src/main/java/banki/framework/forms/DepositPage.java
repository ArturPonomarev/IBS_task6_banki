package banki.framework.forms;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DepositPage extends BaseForm{
    @FindBy(xpath = "//*[@data-test='search-form']")
    WebElement uniqueElement;

    @FindBy(xpath = "//*[@fill-rule]/ancestor::button")
    WebElement depositSettingsButton;

    @Override
    public boolean isPageOpen() {
        return uniqueElement.isEnabled();
    }

    public void openDepositSettings() {
        depositSettingsButton.click();
    }

    public int getDepositCount() {

    }
}
