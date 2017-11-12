package Task09.tests;

import Task09.app.Application;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestBase {
    public Application app;

    @Before
    public void testSetup() {
        app = new Application();
    }

    @After
    public void testCleanup(){
        app.quit();
    }
}
