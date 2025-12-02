// src/test/java/ex1B/basics/pages2/tests/CheckBoxesTests.java
package ex1B.basics.pages2.tests;

import ex1B.basics.pages2.pages.CheckBoxesPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.Assert.*;

public class CheckBoxesTests {
    private WebDriver driver;
    private CheckBoxesPage page;

    @Before
    public void setUp() {
        // Assegure que o chromedriver está no PATH ou defina webdriver.chrome.driver
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        page = new CheckBoxesPage(driver);
    }

    @After
    public void tearDown() {
        if (driver != null) driver.quit();
    }

    @Test
    public void navigationToCheckBoxes() {
        page.open();
        assertTrue(driver.getCurrentUrl().contains("/checkboxes"));
    }

    @Test
    public void checkboxesInitialAndToggle() {
        page.open();
        assertEquals(2, page.getCheckboxes().size());

        boolean first = page.isChecked(0);
        boolean second = page.isChecked(1);

        // toggle first checkbox
        page.toggle(0);
        assertEquals(!first, page.isChecked(0));

        // set second to the opposite of initial
        page.setChecked(1, !second);
        assertEquals(!second, page.isChecked(1));
    }
}
