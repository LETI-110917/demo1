package iscteiul.ista.demo1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// page_url = https://www.jetbrains.com/
public class MainPage {
    @FindBy(xpath = "//*[@data-test-marker='Developer Tools']")
    public WebElement seeDeveloperToolsButton;

    @FindBy(xpath = "//*[@data-test='suggestion-link']")
    public WebElement findYourToolsButton;

    @FindBy(xpath = "//div[@data-test='main-menu-item' and @data-test-marker = 'Developer Tools']")
    public WebElement toolsMenu;

    @FindBy(css = "[data-test='site-header-search-action']")
    public WebElement searchButton;

    // Cookie banner accept button
    @FindBy(css = "button.ch2-btn.ch2-allow-all-btn.ch2-btn-primary")
    public WebElement acceptCookiesButton;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}

//<button class="ch2-btn ch2-allow-all-btn ch2-btn-primary" tabindex="0">Accept All</button>