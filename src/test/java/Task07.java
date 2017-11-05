import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

public class Task07 {
    private ChromeDriver driver;

    @Before
    public void testSetup() {
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.cssSelector("input[type='text']")).sendKeys("admin");
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys("admin");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }

    @Test
    public void countriesNewWindows(){
        WebDriverWait wait = new WebDriverWait(driver,10);

        WebElement countriesMenu = driver.findElements(By.id("app-")).get(2);
        countriesMenu.click();
        WebElement afghanistanRow = driver.findElement(By.cssSelector(".dataTable tr:nth-child(2)>td:nth-child(5) a"));
        afghanistanRow.click();
        String countryWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();

        List<WebElement> externalLinks = driver.findElements(By.cssSelector("i.fa.fa-external-link"));
        for (int i=0; i<externalLinks.size(); i++){
            driver.findElements(By.cssSelector("i.fa.fa-external-link")).get(i).click();
            String newWindow = wait.until(anyWindowOtherThan(allWindows));
            driver.switchTo().window(newWindow);
            driver.close();
            driver.switchTo().window(countryWindow);
        }
    }

    public ExpectedCondition<String> anyWindowOtherThan(Set<String> windows) {
        return new ExpectedCondition<String>() {
            public String apply(WebDriver input) {
                Set<String> handles = driver.getWindowHandles();
                handles.removeAll(windows);
                return handles.size() > 0 ? handles.iterator().next() : null;
            }
        };
    }

    @After
    public void testCleanup(){
        driver.quit();
    }
}
