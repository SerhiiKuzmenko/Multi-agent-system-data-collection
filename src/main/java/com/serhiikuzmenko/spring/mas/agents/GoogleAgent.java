package com.serhiikuzmenko.spring.mas.agents;

import com.serhiikuzmenko.spring.mas.service.UniversityService;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class GoogleAgent implements SyllabusAgent{
    private String lang = "+lang:en";
    public void setLang(String lang){
        this.lang = lang;
    }
    @Override
    public String linkFounder(String desiredSubject, String uni) {
        String link;
        Pattern pattern = Pattern.compile("\\b[a-zA-Z]+\\b");
        Matcher matcher = pattern.matcher(desiredSubject);

        StringBuilder newDesiredSubject = new StringBuilder();

        while(matcher.find()){
            matcher.appendReplacement(newDesiredSubject, "\""+matcher.group()+"\"");
            matcher.appendTail(newDesiredSubject);
        }

        String searchedSubject = newDesiredSubject.toString();

        System.setProperty("webdriver.chrome.driver", "D:\\USER\\JAVA\\sources\\ua\\edu\\sumdu\\j2se\\Multi-agent-system-data-collection\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");

        WebDriver driver = new ChromeDriver();

        try {
            String url = "https://www.google.com/search?q=";


            driver.get(url+uni.replace(" ", "+")+"+syllabus+"+searchedSubject.replace(" ", "+")+lang);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            Thread.sleep(2000);
            WebElement element = null;

            try{
                element = driver.findElement(By.xpath("//a[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'syllabus') and not(ancestor::div[@class='cUnQKe'])]"));
            }catch (NoSuchElementException e){
            }finally {
                if(element==null){
                    return "Not found";
                }else{
                    link = element.getAttribute("href");

                    return link;
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {

            driver.quit();
        }
    }
}
