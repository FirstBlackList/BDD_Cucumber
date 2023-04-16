package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class DashboardPage {

    private final ElementsCollection cards = $$x("//ul[contains(@class,\"list\")]//div");

    public DashboardPage() {
        $(byText("Личный кабинет")).shouldBe(visible);
        $(byText("Ваши карты")).shouldBe(visible);
    }

    public TransferMoneyPage selectCard(String indexCard) {
        cards.get(Integer.parseInt(indexCard) - 1).find("button").click();
        return new TransferMoneyPage();
    }

    public void firstBalanceCard(String index, String balance) {
        if (index == "1") {
            $(withText(balance)).shouldHave(attribute("data-test-id", "92df3f1c-a033-48e6-8390-206f6b1f56c0"));
        } else if (index == "2") {
            $(withText(balance)).shouldHave(attribute("data-test-id", "0f3f5c2a-249e-4c3d-8287-09f7a039391d"));
        }
    }
}
