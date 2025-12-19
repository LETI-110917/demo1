package ex2.bookstore.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide; // Para o sleep
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class BookstorePage {

    // --- Ações ---

    public void clickNewProduct() {
        // Procura um botão que contenha EXATAMENTE o texto "New product" ou aproximado
        // O $(byText) é excelente para botões
        $(byText("New product")).shouldBe(Condition.clickable).click();
    }

    public void fillProductDetails(String name, String price, String stock) {
        // 1. Nome (Index 1) - Geralmente vem vazio, sendKeys direto funciona
        $$("vaadin-text-field").get(1)
                .shouldBe(Condition.visible)
                .sendKeys(name);

        Selenide.sleep(500);

        // 2. Preço (Index 2) - Tem "0.00" por defeito
        var priceField = $$("vaadin-text-field").get(2);

        // Truque: Enviar Ctrl+A (Selecionar tudo) e depois Backspace para limpar
        priceField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
        priceField.sendKeys(price);

        // 3. Stock (Index 3) - Tem "0" por defeito
        var stockField = $$("vaadin-text-field").get(3);

        // Mesma limpeza para o stock
        stockField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
        stockField.sendKeys(stock);
    }

    public void saveProduct() {
        // Clica no botão "Save".
        // Se houverem vários, garantimos que é o que está visível/ativo.
        $(byText("Save")).click();

        // Espera que o botão desapareça (confirmação que o modal fechou)
        $(byText("Save")).should(Condition.disappear);
    }

    // --- Verificações ---

    // ... Imports ...

    // --- Verificações ---

    public void validateProductInList(String productName) {
        var filterField = $$("vaadin-text-field").get(0);

        // 1. Limpar e preencher filtro
        filterField.shouldBe(Condition.visible)
                .sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);

        filterField.sendKeys(productName, Keys.ENTER);

        // 2. Asserção Selenide Direta
        // Se o texto não aparecer em 5 segundos, o teste falha aqui e mostra um screenshot.
        $("vaadin-grid")
                .shouldHave(Condition.text(productName));
    }
}