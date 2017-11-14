import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

@RunWith(Parameterized.class)
public class Task04 {

    @Parameterized.Parameter
    public WebDriver driver;

    @Parameterized.Parameters
    public static WebDriver[] getDriver() {
        ChromeDriverManager.getInstance().setup();
        InternetExplorerDriverManager.getInstance().setup();
        FirefoxDriverManager.getInstance().setup();

        return new WebDriver[]{new FirefoxDriver(), new ChromeDriver(), new InternetExplorerDriver()};
    }

    @Before
    public void testSetup() {
        driver.get("http://localhost/litecart");
    }

    @Test
    public void productPageStyles(){
        // Read and save product properties from the Main page
        String productNameMainPage = driver.findElement(By.cssSelector("#box-campaigns .name")).getText();
        WebElement regularPriceMainPage = driver.findElement(By.cssSelector("#box-campaigns .regular-price"));
        String regPriceMainPage = regularPriceMainPage.getText();
        String regPriceColorMainPage = regularPriceMainPage.getCssValue("color");
        String regPriceStyleMainPage = regularPriceMainPage.getCssValue("text-decoration");
        WebElement campaignPriceMainPage = driver.findElement(By.cssSelector("#box-campaigns .campaign-price"));
        String campPriceMainPage = campaignPriceMainPage.getText();
        String campPriceColorMainPage = campaignPriceMainPage.getCssValue("color");
        String campPriceStyleMainPage = campaignPriceMainPage.getCssValue("font-weight");

        driver.findElement(By.cssSelector("#box-campaigns a.link")).click();

        // Read and save product properties from the Product page
        String productNameProductPage = driver.findElement(By.cssSelector("h1.title")).getText();
        WebElement regularPriceProductPage = driver.findElement(By.cssSelector(".regular-price"));
        String regPriceProductPage = regularPriceProductPage.getText();
        String regPriceColorProductPage = regularPriceProductPage.getCssValue("color");
        String regPriceStyleProductPage = regularPriceProductPage.getCssValue("text-decoration");
        WebElement campaignPriceProductPage = driver.findElement(By.cssSelector(".campaign-price"));
        String campPriceProductPage = campaignPriceProductPage.getText();
        String campPriceColorProductPage = campaignPriceProductPage.getCssValue("color");
        String campPriceStyleProductPage = campaignPriceProductPage.getCssValue("font-weight");

        // Verification for all browsers
        Assert.assertEquals("Product name is different on Main and Product pages.", productNameMainPage, productNameProductPage);
        Assert.assertEquals("Regular price is different on Main and Product pages.", regPriceMainPage, regPriceProductPage);
        Assert.assertEquals("Campaign price is different on Main and Product pages.", campPriceMainPage, campPriceProductPage);
        Assert.assertTrue("Regular price style is not strike on the Main page.", regPriceStyleMainPage.contains("line-through"));
        Assert.assertTrue("Regular price style is not strike on the Product page.", regPriceStyleProductPage.contains("line-through"));

        // Verification specific for Firefox
        if (driver instanceof FirefoxDriver){
            Assert.assertEquals("Regular price color is not gray on the Main page.", "rgb(119, 119, 119)", regPriceColorMainPage);
            Assert.assertEquals("Regular price color is not gray on the Product page.", "rgb(102, 102, 102)", regPriceColorProductPage);
            Assert.assertEquals("Campaign price color is not red on the Main page.", "rgb(204, 0, 0)", campPriceColorMainPage);
            Assert.assertEquals("Campaign price color is not red on the Product page.", "rgb(204, 0, 0)", campPriceColorProductPage);
            Assert.assertEquals("Campaign price style is not bold on the Main page.", "900", campPriceStyleMainPage);
            Assert.assertEquals("Campaign price style is not bold on the Product page.", "700", campPriceStyleProductPage);
        }

        // Verification specific for Chrome
        if (driver instanceof ChromeDriver){
            Assert.assertEquals("Campaign price color is not gray on the Main page.", "rgba(119, 119, 119, 1)", regPriceColorMainPage);
            Assert.assertEquals("Campaign price color is not gray on the Product page.", "rgba(102, 102, 102, 1)", regPriceColorProductPage);
            Assert.assertEquals("Campaign price color is not red on the Main page.", "rgba(204, 0, 0, 1)", campPriceColorMainPage);
            Assert.assertEquals("Campaign price color is not red on the Product page.", "rgba(204, 0, 0, 1)", campPriceColorProductPage);
            Assert.assertEquals("Campaign price style is not bold on the Main page.", "bold", campPriceStyleMainPage);
            Assert.assertEquals("Campaign price style is not bold on the Product page.", "bold", campPriceStyleProductPage);
        }

        // Verification specific for IE
        if (driver instanceof InternetExplorerDriver){
            Assert.assertEquals("Campaign price color is not gray on the Main page.", "rgba(119, 119, 119, 1)", regPriceColorMainPage);
            Assert.assertEquals("Campaign price color is not gray on the Product page.", "rgba(102, 102, 102, 1)", regPriceColorProductPage);
            Assert.assertEquals("Campaign price color is not red on the Main page.", "rgba(204, 0, 0, 1)", campPriceColorMainPage);
            Assert.assertEquals("Campaign price color is not red on the Product page.", "rgba(204, 0, 0, 1)", campPriceColorProductPage);
            Assert.assertEquals("Campaign price style is not bold on the Main page.", "900", campPriceStyleMainPage);
            Assert.assertEquals("Campaign price style is not bold on the Product page.", "700", campPriceStyleProductPage);
        }
    }

    @After
    public void testCleanup(){
        driver.quit();
        driver = null;
    }
}
