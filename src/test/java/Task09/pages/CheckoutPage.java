package Task09.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckoutPage extends PageBase{
    public CheckoutPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void open(){
        driver.findElement(By.cssSelector("a.link[href*='checkout'")).click();
        driver.findElement(By.cssSelector(".shortcut")).click();
    }

    @FindBy(css = "td.item")
    public List<WebElement> cartItems;

//    @FindBy(css = ".dataTable")
//    public WebElement productsTable;

    public WebElement productsTable() {
        return driver.findElement(By.cssSelector(".dataTable"));
    }

    @FindBy(css = "button[value='Remove']")
    public WebElement removeButton;

    @FindBy(css = "em")
    public WebElement noItemsMessage;
}
