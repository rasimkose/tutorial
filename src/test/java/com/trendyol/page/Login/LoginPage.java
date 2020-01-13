package com.trendyol.page.Login;

import com.trendyol.utilities.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }



    private By loginButtonCssSelector = By.cssSelector(".login a");
    private By userEmailId = By.id("customerEmail");
    private By userEmail2Id = By.id("Email2");
    private By passwordId = By.id("customerPassword");
    private By password2Id = By.id("Password");
    private By loginSubmitButtonXPath = By.xpath("//button[contains(text(), 'Giriş Yap')]");



    public LoginPage clickLoginButton(){
        clickObject(loginButtonCssSelector);

        return this;
    }

    public void setLoginData(String userEmail, String password){
        if (isElementDisplayed(userEmail2Id)){
            fillInputField(userEmail2Id, userEmail);
            fillInputField(password2Id, password);
        }else {
            fillInputField(userEmailId, userEmail);
            fillInputField(passwordId, password);
        }

        clickObject(loginSubmitButtonXPath);
    }
}
