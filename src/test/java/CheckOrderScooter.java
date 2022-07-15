import dataForTests.TestDataForOrder;
import dataForTests.URLs;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObject.HomePage;
import pageObject.OrderPage;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static dataForTests.TestDataForOrder.ORDER_NUMBER_IS_NULL;
import static dataForTests.TestDataForOrder.ORDER_NUMBER_IS_ZERO;

@RunWith(Parameterized.class)
public class CheckOrderScooter {

    private WebDriver driver;

    @Before
    public void setUp() {

//        System.setProperty("webdriver.gecko.driver", "D://Downloads//Files//WebDriver//geckodriver.exe");
//        driver = new FirefoxDriver();

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    private String name;
    private String surname;
    private String address;
    private String phone;
    private String station;
    private String date;
    private String daysAmount;
    private String scooterColour;

    public CheckOrderScooter(String name, String surname, String address, String phone, String station, String date, String daysAmount, String scooterColour) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.station = station;
        this.date = date;
        this.daysAmount = daysAmount;
        this.scooterColour = scooterColour;
    }

    @Parameterized.Parameters
    public static Object[][] getTextData() {
        return TestDataForOrder.CLIENT_INFO;
    }

    @Test
    public void checkFillAndSendScooterOrderFromBottomButton() {

        this.driver.get(URLs.HOME_PAGE);

        HomePage homePage = new HomePage(this.driver);

        WebElement element = driver.findElement(homePage.bottomOrderButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(element));

        homePage.clickBottomOrderButton();

        OrderPage orderPage = new OrderPage(this.driver);

        orderPage.fillOrderForm(this.name, this.surname, this.address, this.phone, this.station);
        orderPage.clickNextButton();
        orderPage.fillRentDetailsForm(this.date, this.daysAmount, this.scooterColour);
        orderPage.clickBottomOrderButton();
        orderPage.clickYesButton();

        Assert.assertNotEquals("Окно подтверждения заказа не высветилось", " ", orderPage.getOrderIsCreatedText());
        Assert.assertFalse("Номер заказа нуль", orderPage.getOrderIsCreatedText().contains(ORDER_NUMBER_IS_ZERO));
        Assert.assertFalse("Номер заказа пуст", orderPage.getOrderIsCreatedText().contains(ORDER_NUMBER_IS_NULL));
    }

    @Test
    public void checkFillAndSendScooterOrderFromTopButton() {

        this.driver.get(URLs.HOME_PAGE);

        HomePage homePage = new HomePage(this.driver);

        WebElement element = driver.findElement(homePage.topOrderButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(element));

        homePage.clickTopOrderButton();

        OrderPage orderPage = new OrderPage(this.driver);

        orderPage.fillOrderForm(this.name, this.surname, this.address, this.phone, this.station);
        orderPage.clickNextButton();
        orderPage.fillRentDetailsForm(this.date, this.daysAmount, this.scooterColour);
        orderPage.clickBottomOrderButton();
        orderPage.clickYesButton();

        Assert.assertNotEquals("Окно подтверждения заказа не высветилось", " ", orderPage.getOrderIsCreatedText());
        Assert.assertFalse("Номер заказа нуль", orderPage.getOrderIsCreatedText().contains(ORDER_NUMBER_IS_ZERO));
        Assert.assertFalse("Номер заказа пуст", orderPage.getOrderIsCreatedText().contains(ORDER_NUMBER_IS_NULL));

    }
}