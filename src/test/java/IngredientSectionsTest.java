import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IngredientSectionsTest extends BaseActionsTest {

    @Before
    public void setUp() {
        mainPage.openMainPage();
    }

    @Test
    public void successOpenBunsSectionTest() {
        mainPage
                .openSouseSection()
                .openBunsSection();

        Assert.assertTrue(mainPage.isBunsSectionActive());
    }

    @Test
    public void successOpenSouseSectionTest() {
        mainPage.openSouseSection();

        Assert.assertTrue(mainPage.isSouseSectionActive());
    }

    @Test
    public void successOpenToppingSectionTest() {
        mainPage.openToppingSection();

        Assert.assertTrue(mainPage.isToppingSectionActive());
    }
}
