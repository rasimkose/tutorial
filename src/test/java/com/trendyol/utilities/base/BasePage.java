package com.trendyol.utilities.base;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BasePage {

    private WebDriver driver;
    private WebDriverWait webDriverWait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.webDriverWait = new WebDriverWait(driver, 5);
    }

    public void navigateTo(String webUrl) {
        if (!driver.getCurrentUrl().equals(webUrl)) {
            driver.navigate().to(webUrl);
        }
    }

    protected String getCurrentUrl() {
        return driver.getCurrentUrl().trim();
    }

    protected void threadSleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private WebElement findElement(By by, int... index) {
        WebElement element = null;

        if (index.length == 0) {
            try {
                element = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(by));
            } catch (Exception e) {
                return null;
            }
        } else if (index[0] >= 0) {
            try {
                element = webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by)).get(index[0]);
            } catch (Exception e) {
                return null;
            }
        }
        highLightElement(element);

        return element;
    }

    private void highLightElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
                "color: red; border: 1px dashed red;");
    }

    private void scrollToCenter(WebElement webElement) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})",
                webElement);
    }



    private void waitUntilExpectedElementClickable(WebElement element) {
        try {
            highLightElement(element);
            webDriverWait.until(ExpectedConditions.visibilityOfAllElements(element));
            webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (NullPointerException e) {
            Assert.fail("Nullpointer Exception for:" + element);
            e.getMessage();
        }
    }

    protected void clickObject(By by, int... index) {
        WebElement element;

        try {
            element = findElement(by, index);
            if (element == null){
                throw new RuntimeException("ELEMENT (" + by + (index.length > 0 ? index[0] : "")
                        + ") NOT EXIST; AUTOMATION DATAS MAY BE INVALID!");
            }else {
                waitUntilExpectedElementClickable(element);
                scrollToCenter(element);
                threadSleep(1250);
                element.click();
            }
        }catch (NullPointerException e){
            e.printStackTrace();
            Assert.assertTrue(false, "Nullpointer Exception for:" + by);
        }
    }

    protected void fillInputField(By by, String text, int... index) {
        WebElement element;

        try {
            element = findElement(by, index);
            if (element.isEnabled()) {
                scrollToCenter(element);
                element.clear();
                element.sendKeys(text);
            }
        } catch (NullPointerException e) {
            Assert.assertTrue(false, "Nullpointer Exception for:" + by);
        }
    }

    protected void checkAllImageLink(By by){
        List<WebElement> links = findElementList(by);

        for (WebElement link : links) {
            String url = link.getAttribute("src");

            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) (new URL(url).openConnection());
                httpURLConnection.setRequestMethod("HEAD");
                httpURLConnection.connect();
                 if (httpURLConnection.getResponseCode() >= 400){
                     System.out.println(link);
                 }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected List<WebElement> findElementList(By by) {
        try {
            if (driver.findElements(by).isEmpty()){
                throw new RuntimeException("ELEMENT " + by + " NOT EXIST; AUTOMATION DATAS MAY BE INVALID!");
            }else {
                return driver.findElements(by);
            }
        } catch (NullPointerException e) {
            Assert.assertTrue(false, "Nullpointer Exception for:" + by);
            return null;
        }
    }

    protected int getWebElementsCount(By by, int... index) {
        return findElementList(by).size();
    }

    protected String getWebElementText(By by, int... index) {
        try {
            return findElement(by, index).getText();
        } catch (NullPointerException e) {
            Assert.assertTrue(false, "Nullpointer Exception for:" + by);
            return null;
        }
    }

    protected boolean isElementDisplayed(By by, int... index) {
        boolean found = false;

        try {
            if (findElement(by, index) != null && findElement(by, index).isDisplayed())
                found = true;
        } catch (NullPointerException e) {
            found = false;
        }

        return found;
    }
}
