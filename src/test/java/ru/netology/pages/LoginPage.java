package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataGen;

import static com.codeborne.selenide.Selenide.$;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;

public class LoginPage {
    private SelenideElement loginField = $("[data-test-id=login] input");
    private SelenideElement passwordField = $("[data-test-id=password] input");
    private SelenideElement loginButton = $("[data-test-id=action-login]");
    private SelenideElement error = $("[data-test-id=error-notification] .notification__content");

    public void errorNotification(String expText) {

        error.shouldHave(exactText(expText)).shouldBe(visible);
    }

    public VerificationPage validLogin(DataGen.User info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPwd());
        loginButton.click();
        return new VerificationPage();
    }
}