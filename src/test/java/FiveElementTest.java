import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.Keys;
import java.util.List;
import org.openqa.selenium.opera.OperaOptions;

public class FiveElementTest {
    WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void driverInitiate(){
        OperaOptions option = new OperaOptions();
        option.addArguments("--window-size=1600,900");
        driver = new OperaDriver(option);
    }

    @Test
    public void addPhoneToBasketTest() throws InterruptedException {
        driver.get("https://5element.by");
        WebElement openSearchString = driver.findElement(By.xpath("/html/body/header/div/div[2]/div[3]/div[1]/form/div/input[2]"));
        openSearchString.click();
        WebElement inputString = driver.findElement(By.xpath("//*[@id='q']"));
        inputString.sendKeys("Huawei");
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/div[10]/div/div[1]/div/div[2]/div/div[2]/div/div[1]/div[1]/a")));
        WebElement pickPhone = driver.findElement(By.xpath("/html/body/div[10]/div/div[1]/div/div[2]/div/div[2]/div/div[1]/div[1]/a"));
        pickPhone.click();
        Thread.sleep(40000);
        WebElement closeBanner = driver.findElement(By.xpath("/html/body/div[1]/div[1]"));
        closeBanner.click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//button[@class=\"spec-product-right-button button js-to-cart\"]")));
        WebElement addToBasketButton = driver.findElement(By.xpath("//button[@class=\"spec-product-right-button button js-to-cart\"]"));
        addToBasketButton.click();
        driver.get("https://5element.by/cart");
        Thread.sleep(40000);
        WebElement closeBannerInBasket = driver.findElement(By.xpath("/html/body/div[1]/div[1]"));
        closeBannerInBasket.click();
        List<WebElement> basketItems = driver.findElements(By.xpath("//div[@class=\"cart-products__row cart-products__row_ga\"]"));
        Assert.assertTrue(basketItems.size()>0);

    }
    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
    }

}
