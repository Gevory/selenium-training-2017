import com.google.common.io.Files;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Task08 {
    private EventFiringWebDriver driver;

    @Before
    public void testSetup() {
        ChromeDriverManager.getInstance().setup();
        driver = new EventFiringWebDriver(new ChromeDriver());
        driver.register(new MyEventListener());
        driver.get("http://localhost/litecart/admin");
        // Changed locator to obtain error
        driver.findElement(By.cssSelector("lalala input[type='text']")).sendKeys("admin");
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys("admin");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }

    @Test
    public void clickMenuCheckHeader(){
        List<WebElement> menuItems = driver.findElements(By.id("app-"));

        for (int i=0; i<menuItems.size(); i++){
            WebElement menu = driver.findElements(By.id("app-")).get(i);
            String menuText = menu.getText();
            menu.click();
            Assert.assertTrue("Header for Menu item" + menuText + "is not displayed", driver.findElement(By.cssSelector("h1")).isDisplayed());

            List<WebElement> subMenuItems = driver.findElements(By.cssSelector(".docs li"));
            for (int j=0; j<subMenuItems.size(); j++) {
                WebElement subMenu = driver.findElements(By.cssSelector(".docs li")).get(j);
                String subMenuText = subMenu.getText();
                subMenu.click();
                Assert.assertTrue("Header for SubMenu item" + subMenuText + "is not displayed", driver.findElement(By.cssSelector("h1")).isDisplayed());
            }
        }
    }

    @After
    public void testCleanup(){
        driver.quit();
    }

    public static class MyEventListener extends AbstractWebDriverEventListener{

        @Override
        public void beforeFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println("Starting Find element: " + by);
        }

        @Override
        public void afterFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println(by + " element found");
        }

        @Override
        public void onException(Throwable throwable, WebDriver driver) {
            String filename = "error" + System.nanoTime() + ".png";
            File tempScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            try {
                Files.copy(tempScreenshot, new File(filename));
            }
            catch (IOException ex){
                ex.printStackTrace();
            }
            System.out.println("Exception: " + throwable);
        }
    }
}
