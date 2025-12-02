package iscteiul.ista.demo1;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainPageTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private MainPage mainPage;

    @BeforeEach
    public void setUp() throws InterruptedException {
        driver = new ChromeDriver();
        // Configuração do WebDriverWait com timeout explícito
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get("https://www.jetbrains.com/");


        mainPage = new MainPage(driver);

        if (mainPage.acceptCookiesButton.isDisplayed() && mainPage.acceptCookiesButton.isEnabled())
            mainPage.acceptCookiesButton.click();

        Thread.sleep(1000);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void search() throws InterruptedException {
        // Aguarda o botão de pesquisa ficar clicável
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.searchButton));
        mainPage.searchButton.click();

        // Pequena pausa para garantir que o campo de pesquisa esteja totalmente carregado
        Thread.sleep(1000);

        // Aguarda o campo de pesquisa ficar visível
        WebElement searchField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test-id='search-input']"))
        );

        searchField.sendKeys("Selenium");

        // Aguarda o botão de submit ficar clicável
        WebElement submitButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-test='full-search-button']"))
        );
        submitButton.click();

        // Aguarda o campo de pesquisa na página de resultados ficar visível
        WebElement searchPageField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[data-test-id='search-input']"))
        );

        // Verificação com espera explícita para o valor ser preenchido
        wait.until(ExpectedConditions.attributeToBe(searchPageField, "value", "Selenium"));
        assertEquals("Selenium", searchPageField.getAttribute("value"));
    }

    @Test
    public void toolsMenu() throws InterruptedException {
        // Aguarda o menu de ferramentas ficar clicável
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.toolsMenu));
        mainPage.toolsMenu.click();

        // Pausa estratégica para animação do menu
        Thread.sleep(1500);

        // Aguarda o popup do menu ficar visível
        WebElement menuPopup = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[data-test='main-submenu-suggestion']"))
        );
        assertTrue(menuPopup.isDisplayed());

        // Pausa adicional para observação visual
        Thread.sleep(1000);
    }

    @Test
    public void navigationToAllTools() throws InterruptedException {
        // Aguarda o botão "See Developer Tools" ficar clicável
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.seeDeveloperToolsButton));
        mainPage.seeDeveloperToolsButton.click();

        // Pausa para carregamento da página intermediária
        Thread.sleep(2000);

        // Aguarda o botão "Find Your Tools" ficar clicável
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.findYourToolsButton));
        mainPage.findYourToolsButton.click();

        // Aguarda a lista de produtos ficar visível na nova página
        WebElement productsList = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("products-page"))
        );

        // Verifica se a lista está visível
        assertTrue(productsList.isDisplayed());

        // Aguarda o título da página ser o esperado
        wait.until(ExpectedConditions.titleIs("All Developer Tools and Products by JetBrains"));
        assertEquals("All Developer Tools and Products by JetBrains", driver.getTitle());

        // Pausa final para observação (opcional)
        Thread.sleep(1500);
    }
}