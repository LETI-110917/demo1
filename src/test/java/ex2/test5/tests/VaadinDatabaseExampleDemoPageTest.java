package ex2.test5.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ex2.test5.pages.VaadinDatabaseExampleDemoPage;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;

public class VaadinDatabaseExampleDemoPageTest {

    @BeforeAll
    static void setup() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 6000; // aguarda notificações do Vaadin
    }

    @Test
    void testNativeButtonShowsNotification() {

        VaadinDatabaseExampleDemoPage page = new VaadinDatabaseExampleDemoPage();

        page.openPage();
        page.clickButton();


        String notif = page.getNotificationText();

        assertEquals("The button was clicked", notif);
    }
}
