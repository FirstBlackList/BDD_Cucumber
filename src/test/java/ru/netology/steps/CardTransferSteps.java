package ru.netology.steps;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.TransferMoneyPage;
import ru.netology.page.VerificationPage;


public class CardTransferSteps {
    private static LoginPage loginPage;
    private static DashboardPage dashboardPage;
    private static VerificationPage verificationPage;
    private static TransferMoneyPage transferMoneyPage;

    @Пусть("пользователь залогинен с именем {string} и паролем {string}")
    public void authAndVerification(String login, String password) {
        loginPage = Selenide.open("http://localhost:9999", LoginPage.class);
        verificationPage = loginPage.loginWithValidData(login, password);
        var authInfo = DataHelper.getAuthInfo();
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        dashboardPage = verificationPage.validVerify(verificationCode);
    }

    @Когда("пользователь переводит {string} рублей с карты с номером {string} на свою {string} карту с главной страницы;")
    public void transferCard(String amount, String cardFrom, String indexCardTo) {
        transferMoneyPage = dashboardPage.selectCard(indexCardTo);
        dashboardPage = transferMoneyPage.replenish(amount, cardFrom);
    }

    @Тогда("баланс его {string} карты из списка на главной странице должен стать {string} рублей")
    public void matchesBalance(String indexCard, String balance) {
        dashboardPage.firstBalanceCard(indexCard, balance);
    }
}
