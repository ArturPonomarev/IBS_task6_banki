package banki.tests;

import banki.framework.forms.DepositPage;
import banki.framework.forms.MainPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CommonDepositTest extends BaseTest{

    //TODO: в тестовые данные
    private final String DEPOSIT_MENU_NAME = "Вклады";

    private MainPage mainPage = new MainPage();
    private DepositPage depositPage = new DepositPage();

    @Test
    public void testMethod() {
        Assertions.assertTrue(mainPage.isPageOpen(),"Главная страница не открыта");
        mainPage.openMenu(DEPOSIT_MENU_NAME);
        Assertions.assertTrue(depositPage.isPageOpen(),"Страница вкладов не открыта");
        depositPage.openDepositSettings();
    }
}
