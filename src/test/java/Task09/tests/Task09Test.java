package Task09.tests;

import org.junit.Assert;
import org.junit.Test;

public class Task09Test extends TestBase {
    @Test
    public void addDeleteItemsFromCart() {
        app.mainStorePage.open();

        // Add products to the cart
        for (int i=0; i<4; i++) {
            app.addToCart();
        }

        // Navigate to Checkout
        app.checkoutPage.open();

        // Delete products from cart
        app.deleteAllFromCart();

        // Verify that all items were deleted from cart so 0 items are shown in cart on the main page
        app.mainStorePage.open();
        String numberOfItems = app.mainStorePage.headerBlock.getCartQuantity();
        Assert.assertEquals("0", numberOfItems);
    }
}
