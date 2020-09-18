package com.revature.tier5.answers;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import dev.ranieri.assesors.RevAssess;
import dev.ranieri.assesors.RevaTest;

/**
 * prompt: Dynamically render a flashcard's information obtained from the
 * provided endpoint to the screen
 */
@ExtendWith(RevAssess.class)
public class Answer3Tests {
    private WebDriver wd;
    Map<String, List<String>> jsonMap;

    @BeforeEach
    public void pageSetup() {
        System.setProperty("webdriver.gecko.driver", "src/assets/geckodriver.exe");
        wd = new FirefoxDriver();
        File html = Paths.get("src/main/webapp/html/index.html").toFile();
        wd.navigate().to("file://" + html.getAbsolutePath());
    }

    @BeforeEach
    public void answerSetup() {
        List<String> jsonQues = new ArrayList<>();
        jsonQues.add("core java question");
        jsonQues.add("java reflection question");
        jsonQues.add("java collections question");
        List<String> jsonAns = new ArrayList<>();
        jsonAns.add("dummy answer");
        List<String> jsonCat = new ArrayList<>();
        jsonCat.add("core java");
        jsonCat.add("java reflection");
        jsonCat.add("java collections");
        List<String> jsonIds = new ArrayList<>();
        jsonIds.add("1");
        jsonIds.add("2");
        jsonIds.add("3");
        jsonMap = new HashMap<>();
        jsonMap.put("cardId", jsonIds);
        jsonMap.put("cardQstn", jsonQues);
        jsonMap.put("cardAns", jsonAns);
        jsonMap.put("cardCat", jsonCat);
    }

    @RevaTest(tier = 5, points = 30)
    public void testContent() {
    	try {
        Map<String, String> elMap= new HashMap<>();
        ((JavascriptExecutor)wd).executeScript("document.getElementById('cardId').innerHTML=''");
        ((JavascriptExecutor)wd).executeScript("document.getElementById('cardQstn').innerHTML=''");
        ((JavascriptExecutor)wd).executeScript("document.getElementById('cardAns').innerHTML=''");
        ((JavascriptExecutor)wd).executeScript("document.getElementById('cardCat').innerHTML=''");
        ((JavascriptExecutor)wd).executeScript("manipDom()");
        new WebDriverWait(wd, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='cardQstn']")));
        List<WebElement> elements = wd.findElements(By.xpath("//*[@id='cardId']|//*[@id='cardQstn']|//*[@id='cardAns']|//*[@id='cardCat']"));
        elements.stream().forEach(e-> elMap.put(e.getAttribute("id"), e.getText()));
        wd.close();
        elMap.keySet().stream().forEach(e->assertTrue(jsonMap.get(e).contains(elMap.get(e))));
    	} catch(Exception e) {
    		fail();
    	} finally {
    		wd.close();
    	}
    }

}