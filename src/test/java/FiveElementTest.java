import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Keys;
import java.util.List;
import org.openqa.selenium.chrome.ChromeOptions;

public class FiveElementTest {
    WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void driverInitiate(){
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--disable-notifications");
        option.addArguments("--disable-popup-blocking");
        option.addArguments("--window-size=1600,900");
        driver = new ChromeDriver(option);
    }

    @Test
    public void addPhoneToBasketTest() throws InterruptedException {
        driver.get("https://5element.by");
        WebElement openSearchString = driver.findElement(By.xpath("//div[@class=\"searchbox js-search-main\"]"));
        openSearchString.click();
        WebElement inputString = driver.findElement(By.xpath("//*[@id='q']"));
        inputString.sendKeys("Смартфон Huawei Y9s (STK-L21) светло-голубой");
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//a[@href=\"/products/652963-smartfon-huawei-y9s-stk-l21-svetlo-goluboy?q=смартфон huawei y9s (stk-l21) светло-голубой\"]")));
        WebElement pickPhone = driver.findElement(By.xpath("//a[@href=\"/products/652963-smartfon-huawei-y9s-stk-l21-svetlo-goluboy?q=смартфон huawei y9s (stk-l21) светло-голубой\"]"));
        pickPhone.click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//button[@class=\"spec-product-right-button button js-to-cart\"]")));
        WebElement addToBasketButton = driver.findElement(By.xpath("//button[@class=\"spec-product-right-button button js-to-cart\"]"));
        addToBasketButton.click();
        driver.get("https://5element.by/cart");
        List<WebElement> basketItems = driver.findElements(By.xpath("//div[@class=\"cart-products__row cart-products__row_ga\"]"));
        Assert.assertTrue(basketItems.size()>0);

    }
    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
    }

}
