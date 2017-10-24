import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class GoogleTest {

    @Test
    public void googleTest(){
        //ChromeDriverManager.getInstance().setup();

        ChromeDriver driver = new ChromeDriver();

        driver.get("http://www.google.com");
        driver.findElement(By.xpath("//*[@id='lst-ib']")).sendKeys("WebDriver");
        driver.quit();
    }
}
