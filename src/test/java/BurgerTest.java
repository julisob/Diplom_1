import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Database;
import praktikum.Ingredient;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    Database database = new Database();
    Burger burger;
    int sizeOfIngredientsBurger;

    @Before
    public void createNewTestBurger() {

        burger = new Burger();

        //Заполнение бургера ингредиентами
        for(int i = 0; i <= 5; i++) {
            burger.addIngredient(database.availableIngredients().get(i));
        }

        sizeOfIngredientsBurger = burger.ingredients.size();
    }

    @Mock
    Bun mockBun;

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
        burger.moveIngredient(1, 2);
        Ingredient ingredientAfterMoving = burger.ingredients.get(2);
        assertEquals("Ингредиент не перемещен", ingredientBeforeMoving, ingredientAfterMoving);
    }

    @Test
    public void getPriceTest() {
        burger.setBuns(mockBun);
        Mockito.when(mockBun.getPrice()).thenReturn(100f);
        float actualPrice = burger.getPrice();
        float expectedPrice = 1400;
        assertEquals("Цена рассчитана не верно", expectedPrice, actualPrice, 0.0f);
    }

    @Test
    public void getReceiptTest() {
        burger.setBuns(mockBun);
        List<Ingredient> ingredientList = new ArrayList<>(burger.ingredients);
        Mockito.when(mockBun.getName()).thenReturn("Булочка с кунжутом");
        assertEquals("(==== " + mockBun.getName() + " ====)\r\n" +
                "= sauce " + ingredientList.get(0).getName() + " =\r\n" +
                "= sauce " + ingredientList.get(1).getName() + " =\r\n" +
                "= sauce " + ingredientList.get(2).getName() + " =\r\n" +
                "= filling " + ingredientList.get(3).getName() + " =\r\n" +
                "= filling " + ingredientList.get(4).getName() + " =\r\n" +
                "= filling " + ingredientList.get(5).getName() + " =\r\n" +
                "(==== " + mockBun.getName() + " ====)\r\n" +
                "\r\n" +
                "Price: 1200,000000\r\n", burger.getReceipt());
    }
}