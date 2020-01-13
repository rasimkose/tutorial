package com.trendyol.page.Login;

import com.trendyol.utilities.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private By popUpCloseXPath = By.xpath("//a[contains(@title, 'Close') or contains(@title, 'Kapat')]");
    private By loginButtonXPath = By.xpath("//span[text()='Giriş Yap']");
    private By loginButtonXPath2 = By.xpath("//div[text()='Giriş Yap']");
    private By userEmailId = By.id("email");
    private By passwordId = By.id("password");
    private By loginSubmitButtonId = By.id("loginSubmit");
    private By popUpCloseAfterLoginClassname = By.className("modal-close");

    public LoginPage popUpClose() {
        clickObject(popUpCloseXPath);

        return this;
    }

    public LoginPage clickLoginButton(){
        hoverElement(loginButtonXPath);
        clickObject(loginButtonXPath2);

        return this;
    }

    public void setLoginData(String userEmail, String password){
        fillInputField(userEmailId, userEmail);
        fillInputField(passwordId, password);
        clickObject(loginSubmitButtonId);
        if (isElementDisplayed(popUpCloseAfterLoginClassname)){
            clickObject(popUpCloseAfterLoginClassname);
        }

    }
}
