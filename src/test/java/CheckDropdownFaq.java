import dataForTests.DataForDropdown;
import dataForTests.URLs;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObject.HomePage;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@RunWith(Parameterized.class)
public class CheckDropdownFaq {
    private WebDriver driver;
    private String expected;
    private String index;

    @Before
    public void setUp() {

/*
        System.setProperty("webdriver.gecko.driver", "D://Downloads//Files//WebDriver//geckodriver.exe");
        driver = new FirefoxDriver();
*/
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @After
    public void teardown() {
        driver.quit();
    }

    public CheckDropdownFaq(String expected, String index) {
        this.expected = expected;
        this.index = index;
    }

    @Parameterized.Parameters
    public static Object[][] getTextData() {
        return DataForDropdown.EXPECTED_TEXT;
    }

    @Test
    public void checkTextAppropriateWhenClickOnAccordion() {
        this.driver.get(URLs.HOME_PAGE);
        HomePage homePage = new HomePage(this.driver);

        WebElement element = driver.findElement(By.id("accordion__heading-0"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(element));

        homePage.clickAccordionHeading(this.index);
        String text = homePage.getAccordionText(this.index);
        Assert.assertEquals("Текст не соотвествует ожидаемому", expected, text);
    }
}
