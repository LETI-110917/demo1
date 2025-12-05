package ex2.database.tests;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import ex2.database.pages.DatabasePage;
import org.junit.jupiter.api.*;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;

public class DatabasePageTest {

    private DatabasePage page;

    @BeforeEach
    public void setup() throws Exception {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080"; // correto em 7.6.0
        Configuration.timeout = 7000;

        open(DatabasePage.URL);

        page = new DatabasePage();

        Thread.sleep(1500); // tempo visual
    }

    @AfterEach
    public void tearDown() throws Exception {
        Thread.sleep(1200); // tempo visual
        closeWebDriver();
    }

    // ------------------------------------------------------
    // TESTE 1 — GRID VISÍVEL
    // ------------------------------------------------------
    @Test
    public void gridLoadsCorrectly() throws Exception {

        Thread.sleep(1000);

        page.grid.shouldBe(Condition.visible);
    }

    // ------------------------------------------------------
    // TESTE 2 — CONTEÚDO DA TABELA
    // ------------------------------------------------------
    @Test
    public void moviesAreShownCorrectly() throws Exception {

        Thread.sleep(1000);

        List<String> texts = page.cells.texts();

        System.out.println("CELLS:");
        texts.forEach(System.out::println);

        assertTrue(texts.contains("Law Abiding Citizen"));
        assertTrue(texts.contains("Knives Out"));
        assertTrue(texts.contains("The Last Jedi"));

        assertTrue(texts.contains("2009"));
        assertTrue(texts.contains("2019"));
        assertTrue(texts.contains("2017"));

        assertTrue(texts.contains("F. Gardy Gray"));
        assertTrue(texts.contains("Rian Johnson"));
    }

    // ------------------------------------------------------
    // TESTE 3 — ABRIR LINKS IMDB (3 links)
    // ------------------------------------------------------
    @Test
    public void openImdbLinks() throws Exception {

        Thread.sleep(1000);

        // filtrar apenas links visíveis → ESSENCIAL PARA VAADIN GRID
        var visibleLinks = page.imdbLinks.filter(Condition.visible);

        visibleLinks.shouldHave(CollectionCondition.size(3));

        String mainTab = WebDriverRunner.getWebDriver().getWindowHandle();

        int i = 1;
        for (var link : visibleLinks) {
            System.out.println("A clicar no link #" + i);
            Thread.sleep(900);

            link.click();
            Thread.sleep(1500);

            for (String tab : WebDriverRunner.getWebDriver().getWindowHandles()) {
                if (!tab.equals(mainTab)) {
                    WebDriverRunner.getWebDriver().switchTo().window(tab);
                    break;
                }
            }

            System.out.println("Nova aba -> Título: " + title());
            System.out.println("URL aberta: " + WebDriverRunner.url());

            assertTrue(WebDriverRunner.url().startsWith("http"));

            Thread.sleep(1000);

            closeWindow();
            WebDriverRunner.getWebDriver().switchTo().window(mainTab);

            i++;
        }
    }

}

