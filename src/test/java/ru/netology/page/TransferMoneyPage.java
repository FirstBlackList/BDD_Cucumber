package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransferMoneyPage {
    private final SelenideElement amountField = $("[data-test-id=\"amount\"] .input__control");
    private final SelenideElement numberCardField = $("[data-test-id=\"from\"] .input__control");
    private final SelenideElement replenishButton = $("[data-test-id=\"action-transfer\"]");

    public TransferMoneyPage() {
        $(byText("Личный кабинет")).shouldBe(visible);
        $(byText("Пополнение карты")).shouldBe(visible);
    }

    public DashboardPage replenish(String amount, String indexCardFrom) {
        amountField.setValue(amount);
        numberCardField.setValue(indexCardFrom);
        replenishButton.click();
        $(byText("Ваши карты")).shouldBe(visible);
        return new DashboardPage();
    }
}
