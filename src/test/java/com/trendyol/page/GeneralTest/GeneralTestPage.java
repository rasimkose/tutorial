package com.trendyol.page.GeneralTest;

import com.trendyol.utilities.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GeneralTestPage extends BasePage {

    public GeneralTestPage(WebDriver driver) {
        super(driver);
    }

    public static String productName;

    private By tabLinkClassname = By.className("category-header");
    private By butikLinkCssSelector = By.cssSelector(".image-container img");
    private By productLinkClassname = By.className("prc-picture");
    private By produtNameClassname = By.className("pr-in-nm");
    private By addToCartXPath = By.xpath("//div[contains(text(), 'Sepete Ekle')]");
    private By myCartIconCssSelector = By.cssSelector("#myBasketListItem a");
    private By myCartProductNameClassname = By.className("basketlist-productinfo-description");

    public GeneralTestPage butikLinkImageControl(){
        int count = getWebElementsCount(tabLinkClassname);
        for (int i = 0; i < count; i++){
            waitUntilExpectedElement(tabLinkClassname, i);
            clickObject(tabLinkClassname, i);
            checkAllImageLink(butikLinkCssSelector);
        }

        return this;
    }

    public GeneralTestPage randomButikControl(){
        clickObject(tabLinkClassname, 0);
        waitUntilExpectedElement(butikLinkCssSelector);
        clickObject(butikLinkCssSelector, 0);
        checkAllImageLink(productLinkClassname);

        return this;
    }

    public void randomProductAddCart(){
        clickObject(productLinkClassname);
        productName = getWebElementText(produtNameClassname);
        clickObject(addToCartXPath);
        clickObject(myCartIconCssSelector);
    }

    public String actualProductName(){
        return getWebElementText(myCartProductNameClassname);
    }
}
