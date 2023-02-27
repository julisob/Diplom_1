import org.junit.Test;
import praktikum.IngredientType;

public class IngredientTypeTest {

    @Test
    public void getIngredientTypeIsCorrect() {
        IngredientType type = IngredientType.SAUCE;
        IngredientType.valueOf("SAUCE");
    }
}
