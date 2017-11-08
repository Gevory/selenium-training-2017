import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.Date;

public class Task05 {
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
    public void addNewItem(){
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog");
        driver.findElement(By.cssSelector("a.button[href*=edit_product]")).click();

        // Fill General tab
        driver.findElement(By.cssSelector("label [value='1']")).click();
        String productName = "Cougar" + (new Date()).getTime();
        String uniqueNumber = "1" + (new Date()).getTime();
        driver.findElement(By.cssSelector("input[name='name[en]']")).sendKeys(productName);
        driver.findElement(By.cssSelector("input[name='code']")).sendKeys(uniqueNumber);
        driver.findElement(By.cssSelector("input[value='1-3']")).click();
        driver.findElement(By.cssSelector("input[name=quantity]")).clear();
        driver.findElement(By.cssSelector("input[name=quantity]")).sendKeys("15");
        Select soldOutStatus = new Select(driver.findElement(By.cssSelector("select[name=sold_out_status_id]")));
        soldOutStatus.selectByIndex(0);

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("black_pantera.jpg").getFile());
        WebElement fileField = driver.findElement(By.cssSelector("input[type=file]"));
        fileField.sendKeys(file.getAbsolutePath());

        driver.findElement(By.cssSelector("input[name=date_valid_from]")).sendKeys("11/07/2017");
        driver.findElement(By.cssSelector("input[name=date_valid_to]")).sendKeys("11/07/2019");

        // Fill Information tab
        driver.findElement(By.cssSelector("a[href='#tab-information'")).click();
        Select manufacturer = new Select(driver.findElement(By.cssSelector("select[name=manufacturer_id]")));
        manufacturer.selectByIndex(1);
        driver.findElement(By.cssSelector("input[name=keywords]")).sendKeys("toy, plush, cougar");
        driver.findElement(By.cssSelector("input[name='short_description[en]']")).sendKeys("Black cougar toy.");
        driver.findElement(By.cssSelector(".trumbowyg-editor")).sendKeys("Black plush cougar toy.");
        driver.findElement(By.cssSelector("input[name='head_title[en]']")).sendKeys("Black cougar");
        driver.findElement(By.cssSelector("input[name='meta_description[en]']")).sendKeys("Black cougar toy.");

        // Fill Prices tab
        driver.findElement(By.cssSelector("a[href='#tab-prices'")).click();
        driver.findElement(By.cssSelector("input[name=purchase_price]")).clear();
        driver.findElement(By.cssSelector("input[name=purchase_price]")).sendKeys("25");
        Select priceCurrency = new Select(driver.findElement(By.cssSelector("select[name=purchase_price_currency_code]")));
        priceCurrency.selectByIndex(1);
        driver.findElement(By.cssSelector("input[name='prices[USD]']")).sendKeys("30");
        driver.findElement(By.cssSelector("input[name='prices[EUR]']")).sendKeys("28");
        driver.findElement(By.cssSelector("button[name=save]")).click();

        //Verification of new item presence
        WebElement itemsTable = driver.findElement(By.cssSelector("table.dataTable"));
        Assert.assertTrue("New item is not present in Products table.", itemsTable.getText().contains(productName));
    }

    @After
    public void testCleanup(){
        driver.quit();
    }
}
