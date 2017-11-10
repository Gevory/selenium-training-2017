import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Task06 {
    private ChromeDriver driver;

    @Before
    public void testSetup() {
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
        driver.get("http://localhost/litecart");
    }

    @Test
    public void addDeleteItemsFromCart() {
        WebDriverWait wait = new WebDriverWait(driver,10);

        // Add products to the cart
        for (int i=0; i<4; i++) {
            WebElement product = driver.findElements(By.cssSelector(".price")).get(i);
            product.click();
            int cartQuantity = Integer.parseInt(driver.findElement(By.cssSelector("span.quantity")).getAttribute("textContent"));
            // If there is selection for product size select first value
            if (driver.findElements(By.cssSelector("select")).size() > 0){
                new Select(driver.findElement(By.cssSelector("select"))).selectByIndex(1);
            }
            driver.findElement(By.cssSelector("button[value='Add To Cart']")).click();
            int newCartQuantity = cartQuantity + 1;
            wait.until(ExpectedConditions.attributeToBe(driver.findElement(By.cssSelector("span.quantity")), "textContent", Integer.toString(newCartQuantity)));
            driver.get("http://localhost/litecart");
        }

        // Navigate to Checkout
        driver.findElement(By.cssSelector("a.link[href*='checkout'")).click();
        List<WebElement> cartItems = driver.findElements(By.cssSelector("td.item"));
        driver.findElement(By.cssSelector(".shortcut")).click();

        // Delete products from cart
        for (int j=cartItems.size(); j>0; j--){
            WebElement productsTable = driver.findElement(By.cssSelector(".dataTable"));
            driver.findElement(By.cssSelector("button[value='Remove']")).click();
            wait.until(ExpectedConditions.stalenessOf(productsTable));
        }

        // Verify that all items were deleted from cart and 0 items are shown in cart on the main page
        wait.until(ExpectedConditions.attributeToBe(driver.findElement(By.cssSelector("em")), "textContent" , "There are no items in your cart." ));
        driver.get("http://localhost/litecart");
        String numberOfItems = driver.findElement(By.cssSelector("span.quantity")).getAttribute("textContent");
        Assert.assertEquals("0", numberOfItems);
    }

    @After
    public void testCleanup(){
        driver.quit();
    }
}
