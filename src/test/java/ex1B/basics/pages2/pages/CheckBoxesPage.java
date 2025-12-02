// src/test/java/ex1B/basics/pages2/pages2/CheckBoxesPage.java
package ex1B.basics.pages2.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class CheckBoxesPage {
    private final WebDriver driver;
    private final String url = "https://the-internet.herokuapp.com/checkboxes";
    private final By checkboxes = By.cssSelector("form#checkboxes input[type='checkbox']");

    public CheckBoxesPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(url);
    }

    public List<WebElement> getCheckboxes() {
        return driver.findElements(checkboxes);
    }

    public boolean isChecked(int index) {
        return getCheckboxes().get(index).isSelected();
    }

    public void setChecked(int index, boolean value) {
        WebElement cb = getCheckboxes().get(index);
        if (cb.isSelected() != value) {
            cb.click();
        }
    }

    public void toggle(int index) {
        getCheckboxes().get(index).click();
    }
}

