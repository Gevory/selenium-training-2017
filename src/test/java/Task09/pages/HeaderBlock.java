package Task09.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeaderBlock extends PageBase {
    public HeaderBlock(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "span.quantity")
    public WebElement cartQuantity;

    public String getCartQuantity(){
        return cartQuantity.getAttribute("textContent");
    }
}
