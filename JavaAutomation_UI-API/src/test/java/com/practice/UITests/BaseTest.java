package com.practice.UITests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.time.Duration;

public class BaseTest {
    public static WebDriver driver;
    public static WebDriverWait wd;
    public static Actions actions;
    @BeforeTest
    public void setup(){
        if (driver==null) {
            driver = new ChromeDriver();
        }
        driver.get("http://tablepress.org/demo/");
        driver.manage().window().maximize();
        wd=new WebDriverWait(driver, Duration.ofSeconds(30));
        actions=new Actions(driver);
        //return driver;
    }

    @AfterTest
    public void tearDown(){
        if(driver!=null){
            driver.quit();
        }
    }

}
