package Task09.tests;

import org.junit.Assert;
import org.junit.Test;

public class Task09Test extends TestBase {
    @Test
    public void addDeleteItemsFromCart() {
        // Add products to the cart
        for (int i=1; i<5; i++) {
            app.addToCart();
            Assert.assertEquals(Integer.toString(i), app.getNumberOfProductsInCart());
        }

        // Delete products from cart
        app.deleteAllFromCart();

        // Verify that all items were deleted from cart so 0 items are shown in cart on the main page
        Assert.assertEquals("0", app.getNumberOfProductsInCart());
    }
}
