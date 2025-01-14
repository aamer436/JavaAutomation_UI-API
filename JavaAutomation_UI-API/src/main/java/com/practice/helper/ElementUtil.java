package com.practice.helper;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class ElementUtil {

    private WebDriver driver;

    public ElementUtil(WebDriver driver){

        this.driver=driver;
    }

    public void saveScreenshot(String fileName) throws IOException {
        File screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        File filepath= new File(System.getProperty("user.dir") + "/output/screenshots/" + GeneralMethods.getCurrentTime() + "_" + fileName + ".png");
        FileUtils.copyFile(screenshot,filepath);
    }
    public boolean fn_verify_presence_of_element(String obj_element) {
        By locator = By.xpath(obj_element);
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            element = wait.until(ExpectedConditions.visibilityOf(element)); // Ensure visibility
            element = wait.until(ExpectedConditions.elementToBeClickable(element));
            System.out.println("Element located, xpath=" + obj_element);
            return true;
        } catch (NoSuchElementException | ElementNotInteractableException e) {
            System.out.println("Stacktrac ");

            return false;
        }

    }

    public void fn_set_value_by_xpath(String obj_element, String text) {
        try {
            WebElement element = new WebDriverWait(driver, Duration.ofSeconds(30))
                    .ignoring(NoSuchElementException.class,ElementNotInteractableException.class)
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(obj_element)));
            element.clear();
            element.sendKeys(text);
            System.out.println("Element located, xpath=" + obj_element+" and value set "+text);
        } catch (NoSuchElementException | ElementNotInteractableException e) {
            System.out.println("Stacktrac ");
            e.printStackTrace();
        }

    }
    public void fn_click_by_xpath(String obj_element) {
        try {
            WebElement element = new WebDriverWait(driver, Duration.ofSeconds(30))
                    .ignoring(NoSuchElementException.class,ElementNotInteractableException.class)
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(obj_element)));
            System.out.println("Element located, xpath=" + obj_element);
            element.click();

        } catch (NoSuchElementException | ElementNotInteractableException e) {
            System.out.println("Stacktrac ");
            e.printStackTrace();
        }

    }



}
