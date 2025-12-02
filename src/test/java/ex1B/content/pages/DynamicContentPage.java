package ex1B.content.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DynamicContentPage {

    @FindBy(css = "#content .row")
    public List<WebElement> contentRows;

    public DynamicContentPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
