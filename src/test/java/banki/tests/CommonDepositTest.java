package banki.tests;

import banki.framework.forms.DepositPage;
import banki.framework.forms.DepositSettingsForm;
import banki.framework.forms.MainPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CommonDepositTest extends BaseTest{

    //TODO: в тестовые данные
    private final String DEPOSIT_MENU_NAME = "Вклады";

    private MainPage mainPage = new MainPage();
    private DepositPage depositPage = new DepositPage();
    private DepositSettingsForm depositSetting = new DepositSettingsForm();

    @Test
    public void testMethod() {
        Assertions.assertTrue(mainPage.isPageOpen(),"Главная страница не открыта");
        mainPage.openMenu(DEPOSIT_MENU_NAME);
        Assertions.assertTrue(depositPage.isPageOpen(),"Страница вкладов не открыта");
        depositPage.openDepositSettings();
        Assertions.assertTrue(depositSetting.isPageOpen(),"Форма настройки вклада не открыта");
        depositSetting.inputDepositAmount("1 000 000"); //TODO: test data
        //TODO:Проверка значения в поле
        depositSetting.selectDepositTerm("6 месяцев");//TODO: test data
        depositSetting.selectDepositType("Обычные вклады");//TODO: test data
    }
}
