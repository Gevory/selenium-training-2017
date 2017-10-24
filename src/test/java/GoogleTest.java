import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class GoogleTest {

    @Test
    public void googleTest(){
        //comment below while bit9 locks driver executables
        //ChromeDriverManager.getInstance().setup();

        ChromeDriver driver = new ChromeDriver();

        driver.get("http://www.google.com");
        driver.findElement(By.xpath("//*[@id='lst-ib']")).sendKeys("WebDriver");
        driver.quit();
    }
}
