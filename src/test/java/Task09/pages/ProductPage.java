package Task09.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ProductPage extends PageBase {
    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "button[value='Add To Cart']")
    public WebElement addToCartButton;

    public ProductPage selectSize(){
        if (driver.findElements(By.cssSelector("select")).size() > 0){
            new Select(driver.findElement(By.cssSelector("select"))).selectByIndex(1);
        }
        return this;
    }
}
