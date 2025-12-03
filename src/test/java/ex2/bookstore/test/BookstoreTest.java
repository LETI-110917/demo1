package ex2.bookstore.test;


import ex2.bookstore.page.BookstorePage;
import ex2.bookstore.page.LoginPage;

import org.openqa.selenium.Keys;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookstoreTest {

    @BeforeAll
    public static void setUp() {
        Configuration.baseUrl = "https://vaadin-bookstore-example.demo.vaadin.com";
        Configuration.browser = "chrome";
        Configuration.timeout = 10000;
    }

    @Test
    public void testAddNewProduct() {
        // 1. Abrir e Login
        LoginPage loginPage = open("/", LoginPage.class);
        Selenide.sleep(1000);

        BookstorePage storePage = loginPage.login("admin", "admin");
        Selenide.sleep(2000); // Pausa para ver a lista de livros

        // Dados
        String newBookName = "Software Engineering";

        // 2. Criar Novo Produto
        storePage.clickNewProduct();
        Selenide.sleep(1000); // Ver o modal abrir

        storePage.fillProductDetails(newBookName, "30.00", "50");
        Selenide.sleep(2000); // Ver o formulário preenchido

        storePage.saveProduct();
        Selenide.sleep(2000); // Ver o modal fechar e a lista atualizar

        // 3. Validar
        storePage.validateProductInList(newBookName);

        System.out.println("Teste passou com sucesso!");
    }
}