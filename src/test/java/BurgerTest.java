import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    Burger burger;
    int sizeOfIngredientsBurger;

    @Mock
    Bun mockBun;
    @Mock
    Ingredient mockIngredientSauce;
    @Mock
    Ingredient mockIngredientFilling;

    @Before
    public void createNewTestBurger() {

        burger = new Burger();

        //Заполнение бургера ингредиентами
        for(int i = 0; i <= 2; i++) {
            burger.addIngredient(mockIngredientSauce);
        }

        for(int i = 0; i <= 2; i++) {
            burger.addIngredient(mockIngredientFilling);
        }

        sizeOfIngredientsBurger = burger.ingredients.size();
    }

    @Test
    public void createBurgerTest(){

        burger.setBuns(mockBun);

        assertNotNull("Булочки не добавлены", burger.getBuns());
    }

    @Test
    public void addIngredientTest() {
        int expectedSizeOfIngredientsBurger = sizeOfIngredientsBurger;
        assertEquals("Ингредиенты добавлены не корректно", expectedSizeOfIngredientsBurger, sizeOfIngredientsBurger);
    }

    @Test
    public void removeIngredientTest() {
        burger.removeIngredient(1);
        int expectedSizeOfIngredientsBurger = sizeOfIngredientsBurger - 1;
        sizeOfIngredientsBurger = burger.ingredients.size();
        assertEquals("Ингредиент не удален", expectedSizeOfIngredientsBurger, sizeOfIngredientsBurger);
    }

    @Test
    public void moveIngredientsTest() {
        Ingredient ingredientBeforeMoving = burger.ingredients.get(1);
        burger.moveIngredient(1, 4);
        Ingredient ingredientAfterMoving = burger.ingredients.get(4);
        assertEquals("Ингредиент не перемещен", ingredientBeforeMoving, ingredientAfterMoving);
    }

    @Test
    public void getPriceTest() {
        burger.setBuns(mockBun);
        Mockito.when(mockBun.getPrice()).thenReturn(100f);
        Mockito.when(mockIngredientSauce.getPrice()).thenReturn(50f);
        Mockito.when(mockIngredientFilling.getPrice()).thenReturn(500f);
        float actualPrice = burger.getPrice();
        float expectedPrice = 1850;
        assertEquals("Цена рассчитана не верно", expectedPrice, actualPrice, 0.0f);
    }

    @Test
    public void getReceiptTest() {
        burger.setBuns(mockBun);
        Mockito.when(mockBun.getName()).thenReturn("Булочка с кунжутом");
        Mockito.when(mockIngredientSauce.getName()).thenReturn("Сырный соус");
        Mockito.when(mockIngredientFilling.getName()).thenReturn("Межгалактический сыр");
        Mockito.when(mockIngredientSauce.getType()).thenReturn(SAUCE);
        Mockito.when(mockIngredientFilling.getType()).thenReturn(FILLING);
        Mockito.when(mockBun.getPrice()).thenReturn(100f);
        Mockito.when(mockIngredientSauce.getPrice()).thenReturn(50f);
        Mockito.when(mockIngredientFilling.getPrice()).thenReturn(500f);

        assertEquals("(==== " + mockBun.getName() + " ====)\r\n" +
                "= sauce " + mockIngredientSauce.getName() + " =\r\n" +
                "= sauce " + mockIngredientSauce.getName() + " =\r\n" +
                "= sauce " + mockIngredientSauce.getName() + " =\r\n" +
                "= filling " + mockIngredientFilling.getName() + " =\r\n" +
                "= filling " + mockIngredientFilling.getName() + " =\r\n" +
                "= filling " + mockIngredientFilling.getName() + " =\r\n" +
                "(==== " + mockBun.getName() + " ====)\r\n" +
                "\r\n" +
                "Price: 1850,000000\r\n", burger.getReceipt());
    }
}