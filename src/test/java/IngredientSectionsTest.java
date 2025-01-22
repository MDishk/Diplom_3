import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IngredientSectionsTest extends BaseActionsTest {

    @Before
    public void setUp() {
        mainPage.openMainPage();
    }

    @Test
    @DisplayName("Успешное открытие раздела 'Булки'")
    @Description("Проверка перехода к разделу 'Булки'")
    public void successOpenBunsSectionTest() {
        mainPage
                .openToppingSection()
                .openBunsSection();

        Assert.assertTrue("Раздел 'Булки' неактивен", mainPage.isBunsSectionActive());
    }

    @Test
    @DisplayName("Успешное открытие раздела 'Соусы'")
    @Description("Проверка перехода к разделу 'Соусы'")
    public void successOpenSouseSectionTest() {
        mainPage.openSouseSection();

        Assert.assertTrue("Раздел 'Соусы' неактивен", mainPage.isSouseSectionActive());
    }

    @Test
    @DisplayName("Успешное открытие раздела 'Начинки'")
    @Description("Проверка перехода к разделу 'Начинки'")
    public void successOpenToppingSectionTest() {
        mainPage.openToppingSection();

        Assert.assertTrue("Раздел 'Начинки' неактивен", mainPage.isToppingSectionActive());
    }
}
