package com.serhiikuzmenko.spring.mas.agents;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;

public class SumyStateUniversityAgent implements SyllabusAgent{


    @Override
    public String linkFounder(String desiredSubject, String uni) {
        String link;
        String []words = desiredSubject.split("[\\p{Punct}\\s]");

        String xpathExpression = "//a";

        for (int i=0;i< words.length;++i) {
            xpathExpression += "[contains(text(),'" + words[i] + "')]";
        }

        System.setProperty("webdriver.chrome.driver", "D:\\USER\\JAVA\\sources\\ua\\edu\\sumdu\\j2se\\Multi-agent-system-data-collection\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");

        WebDriver driver = new ChromeDriver();

        try {
            String url = "https://pg.cabinet.sumdu.edu.ua/catalog";


            driver.get(url);

            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement element = null;


            long startTime = System.currentTimeMillis();
            long duration = 30000;
            Thread.sleep(500);
            while (System.currentTimeMillis() - startTime < duration) {
                Thread.sleep(100);
                js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

                try{
                    element = driver.findElement(By.xpath(xpathExpression));
                }catch (NoSuchElementException e){
                    element=null;
                }finally {
                    if(element!=null){
                        break;
                    }else{
                        continue;
                    }
                }
            }
            if(element==null){
                return "Not found";
            }
            link = element.getAttribute("href");

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {

            driver.quit();
        }
        return link;
    }
}
