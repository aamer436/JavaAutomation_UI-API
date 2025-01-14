package com.practice.UITests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TestFindBrokenLinks {
    public static void main(String[] args){
        WebDriver driver=new EdgeDriver();
        driver.navigate().to("https://www.amazon.in");
        List<WebElement> links = driver.findElements(By.xpath("//a"));
        List<String> linkText = new ArrayList<>();
        for(WebElement i:links){
            if(i.getAttribute("href")!=""){
                linkText.add(i.getAttribute("href"));
            }
        }
        System.out.println("Total links "+links.size());
        for (String i:linkText){
            //System.out.println(i);
            verifyLink(i);
        }
        driver.quit();
    }
    public static void verifyLink(String url) {
        try {
            URL link = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) link.openConnection();
            httpURLConnection.setConnectTimeout(3000); // Set connection timeout to 3 seconds
            httpURLConnection.connect();


            if (httpURLConnection.getResponseCode() == 200) {
                System.out.println(url + " - " + httpURLConnection.getResponseMessage());
            } else {
                System.out.println(url + " - " + httpURLConnection.getResponseMessage() + " - " + "is a broken link");
            }
        } catch (Exception e) {
            System.out.println(url + " - " + "is a broken link");
        }
    }
}
