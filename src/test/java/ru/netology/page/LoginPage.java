package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private final SelenideElement loginInput = $("[data-test-id=\"login\"] input");
    private final SelenideElement passwordInput = $("[data-test-id=\"password\"] input");
    private final SelenideElement buttonLogin = $("[data-test-id=\"action-login\"]");

    LoginPage() {
        $(byText("Интернет Банк")).shouldBe(visible);
    }

    public VerificationPage loginWithValidData(String login, String password) {
        loginInput.shouldBe(visible).setValue(login);
        passwordInput.shouldBe(visible).setValue(password);
        buttonLogin.shouldBe(visible).click();
        return new VerificationPage();
    }
}
