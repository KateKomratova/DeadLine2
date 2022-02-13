package ru.netology.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.DbInteractionDbUtils;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class LoginTest {
    @BeforeEach
    public void setUp() {
        Configuration.headless = true;
        open("http://localhost:9999");
    }

    @AfterEach
    public void deleteUp() {
        DbInteractionDbUtils.deleteTables();
    }

    @Test
    void shouldValidLogin() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verifyInfo = DbInteractionDbUtils.getCode();
        verificationPage.validVerify(verifyInfo);
    }

    @Test
    void shouldInvalidLoginAndPassword() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getInvalidAuthInfo();
        loginPage.invalidLoginAndPassword(authInfo);
    }

    @Test
    void shouldInvalidPassword() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getInvalidPassword();
        loginPage.invalidLoginAndPassword(authInfo);
        loginPage.cleanField();
        loginPage.invalidLoginAndPassword(authInfo);
        loginPage.cleanField();
        loginPage.blockedInvalidPassword(authInfo);

    }

}
