package com.practice.UITests;

import com.practice.helper.ElementUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class TestFindBrokenLinks_1 {
    public static void main(String[] args){
        WebDriver driver=new EdgeDriver();
        driver.navigate().to("https://www.amazon.in");
        ElementUtil el=new ElementUtil(driver);
        el.fn_set_value_by_xpath("//*[@id='twotabsearchtextbox']","Mobiles");
        el.fn_click_by_xpath("//*[@id='nav-search-submit-button']");
        driver.quit();
    }
}
