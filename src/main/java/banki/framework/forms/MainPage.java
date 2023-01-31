package banki.framework.forms;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MainPage extends BaseForm{

    private final String menuNameAttribute = "data-title";

    @FindBy(xpath = "//*[@data-slide-id='microloans']")
    WebElement uniqueElement;

    @FindBy(className = "main-menu__sections-link")
    List<WebElement> menuButtons;

    @Override
    public boolean isPageOpen() {
        return uniqueElement.isEnabled();
    }

    public void openMenu(String menuName) {
        var result = menuButtons.stream()
                .filter(el -> el.getAttribute(menuNameAttribute).equals(menuName))
                .findFirst();

        result.ifPresentOrElse(
                WebElement::click,
                () -> Assertions.fail("Не существует меню с именем " + menuName));
    }
}
