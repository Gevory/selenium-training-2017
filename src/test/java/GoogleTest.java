import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

@RunWith(Parameterized.class)
public class GoogleTest {

    @Parameterized.Parameter
    public  WebDriver driver;

    @Parameterized.Parameters
    public static WebDriver[] getDriver() {
        ChromeDriverManager.getInstance().setup();
        InternetExplorerDriverManager.getInstance().setup();
        FirefoxDriverManager.getInstance().setup();

        return new WebDriver[]{new FirefoxDriver(), new ChromeDriver(), new InternetExplorerDriver()};
    }

    @Test
    public void googleTest(){
            driver.get("http://www.google.com");
            driver.findElement(By.xpath("//*[@id='lst-ib']")).sendKeys("WebDriver" + Keys.ENTER);
    }

    @After
    public void testCleanup(){
        driver.quit();
        driver = null;
    }
}
