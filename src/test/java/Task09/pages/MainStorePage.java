package Task09.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MainStorePage extends PageBase {

    public MainStorePage(WebDriver driver) {
        super(driver);
    }

    public void open(){
        driver.get("http://localhost/litecart");
    }

    public WebElement cartQuantity(){
    return driver.findElement(By.cssSelector("span.quantity"));
    }

    public List<WebElement> products() {
        return driver.findElements(By.cssSelector(".price"));
    }

    public String getCartQuantity(){
        return cartQuantity().getAttribute("textContent");
    }
}
