import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import praktikum.Bun;

public class BunTest {
    private final String bunName = "С кунжутом";
    private final float bunPrice = 85;
    Bun bun;

    @Before
    public void createNewBun() {
        bun = new Bun(bunName, bunPrice);
    }

    @Test
    public void getBunNameReturnCorrectValue() {
        String actualName = bun.getName();
        Assert.assertEquals(bunName, actualName);
    }

    @Test
    public void getBunPriceReturnCorrectValue() {
        float actualPrice = bun.getPrice();
        Assert.assertEquals(bunPrice, actualPrice, 0.0f);
    }
}
