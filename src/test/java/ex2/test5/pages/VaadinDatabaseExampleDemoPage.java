package ex2.test5.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.byClassName;

public class VaadinDatabaseExampleDemoPage {


    private final SelenideElement nativeButton =
            $(".v-nativebutton");


    private final SelenideElement notificationCaption =
            $(byClassName("v-Notification-caption"));


    public void openPage() {
        com.codeborne.selenide.Selenide.open(
                "https://demo.vaadin.com/sampler/#ui/interaction/native-button"
        );
    }


    public void clickButton() {
        nativeButton.click();
    }


    public String getNotificationText() {
        return notificationCaption.getText();
    }
}
