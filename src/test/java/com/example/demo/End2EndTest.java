package com.example.demo;

import org.assertj.core.api.Assertions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class End2EndTest {

    private static WebDriver driver;

    @BeforeClass
    public static void initWebDriver() {
        // télécharger sur http://chromedriver.chromium.org/downloads
        System.setProperty("webdriver.chrome.driver", "C:\\dev\\soft\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @AfterClass
    public static void shutDownDriver() {
        driver.quit();
    }

    @Test
    public void demo() throws InterruptedException {
        driver.get("http://localhost:4200");

        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        int nbFactures = driver.findElements(By.cssSelector("app-facture")).size();
        Assertions.assertThat(nbFactures).isEqualTo(3);

        driver.findElement(By.linkText("Acheter")).click();

        int nbArticles = driver.findElements(By.cssSelector("app-acheter select option")).size();
        Assertions.assertThat(nbArticles).isEqualTo(3);

        WebElement element = driver.findElement(By.cssSelector("app-acheter select"));
        new Select(element).selectByVisibleText("Playmobil Hydravion de Police");
        driver.findElement(By.cssSelector("input")).sendKeys("10");
        driver.findElement(By.cssSelector("#add")).click();

        driver.findElement(By.cssSelector("#acheter")).click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

        int nbFacturesBis = driver.findElements(By.cssSelector("app-facture")).size();
        Assertions.assertThat(nbFacturesBis).isEqualTo(4);
    }
}
