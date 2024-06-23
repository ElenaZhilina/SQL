package ru.netology.test;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.netology.data.DataGen;
import ru.netology.data.SQLHelper;
import ru.netology.pages.LoginPage;


import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.SQLHelper.cleanCode;
import static ru.netology.data.SQLHelper.cleanDB;

public class LoginTest {
    private static final Logger log = LoggerFactory.getLogger(LoginTest.class);
    LoginPage loginPage;

    @BeforeEach
    void setUp() {
        loginPage = open("http://localhost:9999", LoginPage.class);
    }

    @AfterEach
    void tearDown() {
        cleanCode();
    }

    @AfterAll
    static void tearDownAll() {
        cleanDB();
    }

    @Test
    void successLogin() {
        var userInfo = DataGen.getUser();
        var verificationPage = loginPage.validLogin(userInfo);
        verificationPage.verificationPageVisibility();
        var verificationCode = SQLHelper.getCode();
        verificationPage.validVerify(verificationCode);
    }

    @Test
    void errorIfUnsuccessLogin() {
        var userInfo = DataGen.getFakeUser();
        loginPage.validLogin(userInfo);
        loginPage.errorNotification("Ошибка! \nНеверно указан логин или пароль");
    }

    @Test
    void errorIfValidLoginAndRandomCode() {
        var userInfo = DataGen.getUser();
        var verificationPage = loginPage.validLogin(userInfo);
        verificationPage.verificationPageVisibility();
        var verificationCode = DataGen.randomCode();
        verificationPage.verify(verificationCode.getCode());
        verificationPage.errorNotification("Ошибка! \nНеверно указан код! Попробуйте ещё раз.");
    }
}
