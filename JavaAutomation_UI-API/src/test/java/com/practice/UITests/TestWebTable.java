package com.practice.UITests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;

public class TestWebTable extends BaseTest {

    String nameHeader="//span[@class='dt-column-title' and text()='Name']";
    String nameValue="//tbody//tr//td[1]";
    String showMore="//button[text()='Show more']";
    String searchField="//label[contains(text(),'Search')]/following-sibling::input";
    String exactBtn="//button[text()='Exact']";
    @Test(priority=1)
    public void testGetNames() throws InterruptedException {
        wd.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(nameHeader))).click();
        //List<WebElement> elements =wd.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(nameValue)));
        List<WebElement> elements=new ArrayList<>();
        for(int i=1;i<=3;i++) {
            WebElement sh=wd.until(ExpectedConditions.presenceOfElementLocated(By.xpath(showMore)));
            actions.moveToElement(sh).click().build().perform();
            elements = wd.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(nameValue)));
            System.out.println("Length of list " + elements.size());
            Thread.sleep(2000);
        }
        elements.stream().forEach(e->System.out.println(e.getText()));
    }
    @Test(priority = 2)
    public void testSearchFunctionality(){
        WebElement srch=wd.until(ExpectedConditions.presenceOfElementLocated(By.xpath(searchField)));
        actions.moveToElement(srch).build().perform();
        srch.click();
        srch.sendKeys("cade");
        //actions.moveToElement(srch).click();
        actions.moveToElement(driver.findElement(By.xpath(exactBtn))).click().build().perform();
        List<WebElement> elements = wd.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(nameValue)));
        System.out.println("Length of result set "+elements.size());
    }
}
