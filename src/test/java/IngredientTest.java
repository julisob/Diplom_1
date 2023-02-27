import org.junit.Assert;
import org.junit.Before;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Ingredient;
import praktikum.IngredientType;

@RunWith(Parameterized.class)
public class IngredientTest {
    Ingredient ingredient;
    private IngredientType type;
    private String name;
    private float price;

    @Before
    public void createNewIngredient() {
        ingredient = new Ingredient(type, name, price);
    }

    public IngredientTest(String name, IngredientType type, float price){

        this.name = name;
        this.type = type;
        this.price = price;
    }

    @Parameterized.Parameters(name = "Проверка ингредиентов. Тестовые данные: {0} {1} {2}")
    public static Object[][] checkIngredient() {

        return new Object[][] {
                {"hot sauce", IngredientType.SAUCE, 100},
                {"sour cream", IngredientType.SAUCE, 200},
                {"dinosaur", IngredientType.FILLING, 200},
                {"sausage", IngredientType.FILLING, 300}
        };
    }

    @Test
    public void getIngredientNameReturnCorrectValue() {
        String actualName = ingredient.getName();
        Assert.assertEquals(name, actualName);
    }

    @Test
    public void getIngredientPriceReturnCorrectValue() {
        float actualPrice = ingredient.getPrice();
        Assert.assertEquals(price, actualPrice, 0.0f);
    }

    @Test
    public void getIngredientTypeReturnCorrectValue() {
        IngredientType actualType = ingredient.getType();
        Assert.assertEquals(type, actualType);
    }
}
