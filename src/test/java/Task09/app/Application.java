package Task09.app;

import Task09.pages.CheckoutPage;
import Task09.pages.MainStorePage;
import Task09.pages.ProductPage;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Application {
    private WebDriver driver;
    private WebDriverWait wait;
    public MainStorePage mainStorePage;
    public ProductPage productPage;
    public CheckoutPage checkoutPage;

    public Application() {
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,10);
        mainStorePage = new MainStorePage(driver);
        productPage = new ProductPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }

    public void quit(){
        driver.quit();
    }

    public void addToCart(){
        mainStorePage.products().get(0).click();
        int cartQuantity = Integer.parseInt(productPage.headerBlock.getCartQuantity());
        productPage.selectSize().addToCartButton.click();
        int newCartQuantity = cartQuantity + 1;
        wait.until(ExpectedConditions.attributeToBe(productPage.headerBlock.cartQuantity, "textContent", Integer.toString(newCartQuantity)));
        mainStorePage.open();
    }

    public void deleteAllFromCart(){
        for (int j=checkoutPage.cartItems.size(); j>0; j--){
            WebElement productsTable = checkoutPage.productsTable();
            checkoutPage.removeButton.click();
            wait.until(ExpectedConditions.stalenessOf(productsTable));
        }
        wait.until(ExpectedConditions.attributeToBe(checkoutPage.noItemsMessage, "textContent" , "There are no items in your cart." ));
    }
}
