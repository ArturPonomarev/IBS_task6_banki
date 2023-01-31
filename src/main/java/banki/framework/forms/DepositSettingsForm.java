package banki.framework.forms;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Optional;

public class DepositSettingsForm extends BaseForm {

    @FindBy(xpath = "//*[contains(@class,'SearchModal')]//*[contains(text(),'Тип вклада')]/..//*[@role='button']")
    WebElement depositTypeListOpener;

    @FindBy(xpath = "//*[contains(@class,'SearchModal')]//*[contains(text(),'Срок')]/..//*[@role='button']")
    WebElement depositTermListOpener;

    @FindBy(xpath = "//*[contains(@class,'SearchModal')]//input[@name='amount']")
    WebElement depositAmountField;

    @FindBy(xpath = "//*[contains(@class,'SearchModal__StyledBody')]")
    WebElement uniqueElement;

    @FindBy(xpath = "//*[contains(@class,'SearchModal')]//*[@data-placement]//li//*[text()]")
    List<WebElement> dropdownElements;

    @Override
    public boolean isPageOpen() {
        return uniqueElement.isEnabled();
    }

    public void inputDepositAmount(String depositAmount) {
        depositAmountField.sendKeys(depositAmount);
    }

    public void selectDepositTerm(String term) {
        depositTermListOpener.click();

        var termListElement = findInDropdown(term);

        termListElement.ifPresentOrElse(
                WebElement::click,
                () -> Assertions.fail("В списке 'Срок' не найден элемент " + term));
    }

    public void selectDepositType(String type) {
        depositTypeListOpener.click();

        var typeListElement = findInDropdown(type);

        typeListElement.ifPresentOrElse(
                WebElement::click,
                () -> Assertions.fail("В списке 'Тип вклада' не найден элемент " + type));
    }

    private Optional<WebElement> findInDropdown(String target) {
        Optional<WebElement> el = Optional.empty();
        try {
            wait.until(dr -> dropdownElements.stream()
                    .anyMatch(element -> element.getText().equals(target)));

            el = dropdownElements.stream()
                    .filter(element -> element.getText().equals(target))
                    .findFirst();
        } catch (TimeoutException ignore) {
        }
        return el;
    }
}
