package banki.framework.forms;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Optional;

public class DepositSettingsForm extends BaseForm {

    String clickOptionCheckboxByTextTemplate = "//*[contains(@class,'SearchModal')]//*[contains(text(),'%s')]/../*[@data-testid='checkbox-icon-wrapper']";

    @FindBy(xpath = "//*[contains(@class,'SearchModal__StyledBody')]")
    WebElement uniqueElement;

    @FindBy(xpath = "//*[contains(@class,'SearchModal')]//*[contains(text(),'Тип вклада')]/..//*[@role='button']")
    WebElement depositTypeListOpener;

    @FindBy(xpath = "//*[contains(@class,'SearchModal')]//*[contains(text(),'Срок')]/..//*[@role='button']")
    WebElement depositTermListOpener;

    @FindBy(xpath = "//*[contains(@class,'SearchModal')]//input[@name='amount']")
    WebElement depositAmountField;

    @FindBy(xpath = "//*[text()='Банки']/../input")
    WebElement banksListOpener;

    @FindBy(xpath = "//*[@data-placement = 'bottom']")
    WebElement anyOpenedList;

    @FindBy(xpath = "//*[contains(@class,'ModalBody')]//button")
    WebElement showBanksButton;

    @FindBy(xpath = "//*[contains(@class,'SearchModal')]//*[@data-placement]//li//*[text()]")
    List<WebElement> dropdownElements;

    @FindBy(xpath = "//*[@checked]/..//*[text()]")
    List<WebElement> selectedBanks;


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

    public void openBanksList() {
        banksListOpener.click();

        try {
            wait.until(dr-> anyOpenedList.isDisplayed());
        } catch (TimeoutException e) {
            Assertions.fail("Список банков не открыт");
        }
    }

    public void closeBanksList() {
        banksListOpener.click();
    }

    public void selectBanks(List<String> bankNames) {
        for (var bankName : bankNames) {
            var bankCheckbox = findInDropdown(bankName);

            bankCheckbox.ifPresentOrElse(
                    WebElement::click,
                    () -> Assertions.fail("В списке 'Банки' не найден элемент " + bankName));

            if (!isBankSelected(bankName))
                Assertions.fail("Банк: " + bankName + " отсутствует в списке выбранных банкой");
        }
    }

    public void selectAdvanceOption(String optionName) {
        try {
            wait.until(dr -> dr.findElement(By.xpath(String.format(clickOptionCheckboxByTextTemplate,optionName)))).click();
        } catch (TimeoutException e) {
            Assertions.fail("В списке опций отсутствует элемент " + optionName);
        }
    }

    public void clickShowButton() {
        showBanksButton.click();
    }

    private boolean isBankSelected(String bankName) {
        try {
            wait.until(dr -> selectedBanks.stream().anyMatch(element -> element.getText().equals(bankName)));
            System.out.println("depositSettings,isbankSelected: Найден банк " + bankName);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
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
            //* Нам не нужно обрабатывать ошибку т.к. по факту она является лишь маркером того что элемент не найден и Optional будет с null'ом
        }
        return el;
    }
}
