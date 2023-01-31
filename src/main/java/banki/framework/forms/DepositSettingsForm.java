package banki.framework.forms;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DepositSettingsForm extends BaseForm{



    @FindBy(xpath = "//*[contains(@class,'SearchModal')]//input[@name='amount']")
    WebElement depositAmountField;

    @FindBy(className = "SearchModal__StyledBody")
    WebElement uniqueElement;

    @Override
    public boolean isPageOpen() {
        return uniqueElement.isEnabled();
    }

    public void inputDepositAmount(String depositAmount) {
        depositAmountField.sendKeys(depositAmount);
    }


}
