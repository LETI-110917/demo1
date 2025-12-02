package ex1B.basics.pages.tests;

import ex1B.basics.pages.pages.InputsPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class InputsTests {

    private WebDriver driver;
    private InputsPage page;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        page = new InputsPage(driver);
        page.open();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void inputAcceptsNumbers() {
        page.inputField.clear();
        page.inputField.sendKeys("12345");
        assertEquals("12345", page.inputField.getAttribute("value"));
    }

    @Test
    public void inputAcceptsOverwrite() {
        page.inputField.clear();
        page.inputField.sendKeys("999");
        assertEquals("999", page.inputField.getAttribute("value"));
    }
}
