package ex2.database.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DatabasePage {

    public static final String URL = "https://vaadin-database-example.demo.vaadin.com/";

    // grid principal
    public SelenideElement grid = $("vaadin-grid");

    // todas as células
    public ElementsCollection cells = $$("vaadin-grid-cell-content");

    // apenas links IMDB visíveis
    public ElementsCollection imdbLinks = $$("vaadin-grid-cell-content a");
}
