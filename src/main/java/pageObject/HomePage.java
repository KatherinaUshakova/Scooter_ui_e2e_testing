package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;

    public By bottomOrderButton = By.xpath(".//div/div/div/div[4]/div[2]/div[5]/button");
    public By topOrderButton = By.xpath((".//div/div/div/div[1]/div[2]/button[1]"));

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public void clickAccordionHeading(String index) {
        driver.findElement(By.id("accordion__heading-" + index)).click();
    }

    public String getAccordionText(String index){
       return driver.findElement(By.id("accordion__panel-" + index)).getText();
    }

    public void clickBottomOrderButton(){
        driver.findElement(bottomOrderButton).click();
    }

    public void clickTopOrderButton(){
        driver.findElement(topOrderButton).click();
    }

}
