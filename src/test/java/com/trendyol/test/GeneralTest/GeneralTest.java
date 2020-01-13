package com.trendyol.test.GeneralTest;

import com.trendyol.page.GeneralTest.GeneralTestPage;
import com.trendyol.page.Login.LoginPage;
import com.trendyol.utilities.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GeneralTest extends BaseTest {

    @Test()
    public void loginTestPass(){
        LoginPage loginPage = new LoginPage(webDriver);
        GeneralTestPage generalTestPage = new GeneralTestPage(webDriver);

        loginPage.navigateTo("https://www.boyner.com.tr/");
        loginPage.clickLoginButton()
                .setLoginData("trendyoltestrk@gmail.com", "test.123");

        generalTestPage.butikLinkImageControl()
                .randomButikControl()
                .randomProductAddCart();

        Assert.assertEquals(generalTestPage.expectedProductName(), GeneralTestPage.productName);
    }
}
