package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class OrderPage {

    private final WebDriver driver;

    public OrderPage(WebDriver driver){
        this.driver = driver;
    }

    public By name = By.xpath(".//div/div/div[2]/div[2]/div[1]/input");
    public By surname = By.xpath(".//div/div/div[2]/div[2]/div[2]/input");
    public By address = By.xpath(".//div/div/div[2]/div[2]/div[3]/input");
    public By phone = By.xpath(".//div/div/div[2]/div[2]/div[5]/input");
    public By stationInput = By.xpath(".//div/div/div[2]/div[2]/div[4]/div/div/input");
    public By nextButton = By.xpath(".//div/div/div[2]/div[3]/button");
    public By orderDateInput = By.xpath(".//div/div/div[2]/div[2]/div[1]/div[1]/div/input");
    public By rentPeriodInput = By.xpath(".//div/div/div[2]/div[2]/div[2]/div[1]/div[1]");
    public By bottomOrderButton = By.xpath(".//div/div/div[2]/div[3]/button[2]");
    public By yesButton = By.xpath(".//div/div/div[2]/div[5]/div[2]/button[2]");
    public By orderIsCreatedText = By.xpath(".//div/div/div[2]/div[5]/div[1]/div");


    public By setOrderDate(String orderDate){
        return By.xpath(".//div/div[text()=" + orderDate + "]");
    }

    public By getRentPeriod(String daysAmount){
        return By.xpath(String.format("//div[@class = 'Dropdown-option' and text()='%s']", daysAmount));
    }

    public By getScooterColour(String scooterColour){
        return By.id(scooterColour);
    }

    public void fillOrderForm(String name, String surname, String address, String phone, String station){
        driver.findElement(this.name).sendKeys(name);
        driver.findElement(this.surname).sendKeys(surname);
        driver.findElement(this.address).sendKeys(address);
        driver.findElement(this.phone).sendKeys(phone);
        driver.findElement(stationInput).click();
        driver.findElement(stationInput).sendKeys(station + Keys.ARROW_DOWN + Keys.ENTER);
    }

    public void clickNextButton(){
        driver.findElement(this.nextButton).click();
    }

    public void fillRentDetailsForm(String date, String daysAmount, String scooterColour){
        driver.findElement(orderDateInput).click();
        driver.findElement(setOrderDate(date)).click();
        driver.findElement(rentPeriodInput).click();
        driver.findElement(getRentPeriod(daysAmount)).click();
        driver.findElement(getScooterColour(scooterColour)).click();
    }

    public void clickBottomOrderButton(){
        driver.findElement(bottomOrderButton).click();
    }

    public void clickYesButton(){
        driver.findElement(yesButton).click();
    }

    public String getOrderIsCreatedText(){
       return driver.findElement(orderIsCreatedText).getText();
    }
}
