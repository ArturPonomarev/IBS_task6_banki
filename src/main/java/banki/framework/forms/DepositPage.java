package banki.framework.forms;

import banki.framework.utils.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DepositPage extends BaseForm{

    String openAdditionalDepositLocator = "//*[contains(text(),'%s')]/ancestor::*[contains(@class,'SearchResults')]//*[@data-role='link']";

    @FindBy(xpath = "//*[@data-test='search-form']")
    WebElement uniqueElement;

    @FindBy(xpath = "//*[@fill-rule]/ancestor::button")
    WebElement depositSettingsButton;

    @FindBy(xpath = "//*[contains(text(),'подобрано')]")
    WebElement foundDepositCount;

    @Override
    public boolean isPageOpen() {
        return uniqueElement.isEnabled();
    }

    public void openDepositSettings() {
        depositSettingsButton.click();
    }

    public int getFoundedDepositCount() {
        return StringUtils.convertStringToInt(foundDepositCount.getText());
    }

    public void openAdditionalDeposit(String bankName) {
        By locator = new By.ByXPath(String.format(openAdditionalDepositLocator,bankName));
        //TODO: Поменять реализацию
        var elements = driver.findElements(locator);
        if (elements.size()>0)
            elements.get(0).click();
    }
}
