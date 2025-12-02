package ex1B.basics.pages2.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InputsPage {

    private WebDriver driver;

    @FindBy(tagName = "input")
    public WebElement inputField;

    public InputsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get("https://the-internet.herokuapp.com/inputs");
    }
}
