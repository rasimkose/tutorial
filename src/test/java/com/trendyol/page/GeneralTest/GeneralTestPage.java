package com.trendyol.page.GeneralTest;

import com.trendyol.utilities.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GeneralTestPage extends BasePage {

    public GeneralTestPage(WebDriver driver) {
        super(driver);
    }

    private By tabLinkCssSelector = By.cssSelector(".main-navigation .menu-category");
    private By butikLinkImageSrcCssSelector = By.cssSelector(".col-lg-4 img");
    private By butikLinkDetailImageSrcClassname = By.className("lazy");
    private By productItemClassname = By.className("product-list-item");
    private By chooseSizeXPath = By.xpath("//span[contains(text(),'Lütfen Beden Seçiniz')]");
    private By chooseSize2CssSelector = By.cssSelector(".dropdown-menu li");
    private By addToCartClassname = By.className("js-addCart");

    public GeneralTestPage butikLinkImageControl(){
        int count = getWebElementsCount(tabLinkCssSelector);
        for (int i = 0; i < count - 2; i++){
            clickObject(tabLinkCssSelector, i);
            checkAllImageLink(butikLinkImageSrcCssSelector);
        }

        return this;
    }

    public GeneralTestPage randomButikControl(){
        clickObject(tabLinkCssSelector, 0);
        clickObject(butikLinkImageSrcCssSelector, 0);
        checkAllImageLink(butikLinkDetailImageSrcClassname);

        return this;
    }

    public static String productName;
    public void randomProductAddCart(){
        clickObject(productItemClassname,3);
        clickObject(chooseSizeXPath);
        clickObject(chooseSize2CssSelector);
        productName = getWebElementText(By.className("black-v1"));
        clickObject(addToCartClassname);
        threadSleep(4000);
        clickObject(By.cssSelector(".basket a"));
    }

    public String expectedProductName(){
        return getWebElementText(By.className("o-productInfo__link"));
    }
}
