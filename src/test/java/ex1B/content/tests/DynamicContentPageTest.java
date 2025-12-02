package ex1B.content.tests;

import ex1B.content.pages.DynamicContentPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class DynamicContentPageTest {

    private WebDriver driver;
    private DynamicContentPage dynamicPage;

    @BeforeEach
    public void setUp() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://the-internet.herokuapp.com/dynamic_content");

        dynamicPage = new DynamicContentPage(driver);

        Thread.sleep(1000);

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void contentChangesAfterRefresh() throws InterruptedException {
        String before = dynamicPage.contentRows.get(0).getText();

        driver.navigate().refresh();

        Thread.sleep(1000);

        dynamicPage = new DynamicContentPage(driver);

        String after = dynamicPage.contentRows.get(0).getText();

        assertNotEquals(before, after);
    }
}
