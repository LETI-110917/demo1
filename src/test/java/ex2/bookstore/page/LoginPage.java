package ex2.bookstore.page;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class LoginPage {

    // Não precisamos de definir campos como propriedades da classe
    // Podemos defini-los diretamente nos métodos ou como constantes privadas

    public BookstorePage login(String user, String pass) {
        // $(byName("...")) é equivalente a driver.findElement(By.name("..."))
        // mas muito mais inteligente.

        $(byName("username")).sendKeys(user);
        $(byName("password")).sendKeys(pass);

        // Usamos pressEnter() para evitar problemas de clique no botão
        $(byName("password")).pressEnter();

        return page(BookstorePage.class);
    }
}