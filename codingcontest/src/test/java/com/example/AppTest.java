package com.example;

import java.io.File;
import java.io.FileInputStream;
import org.openqa.selenium.JavascriptExecutor;
import org.apache.commons.io.FileUtils;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Unit test for simple App.
 */
public class AppTest {
    WebDriver driver;

    @BeforeMethod
    public void Testsetup() throws Exception {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.barnesandnoble.com/");
    }

    @Test(priority = 0)
    public void Testcase1() throws Exception {
        WebElement dropdown = driver
                .findElement(By.xpath("//*[@id='rhf_header_element']/nav/div/div[3]/form/div/div[1]/a"));
        dropdown.click();
        Thread.sleep(2000);
        driver.findElement(By.linkText("Books")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"rhf_header_element\"]/nav/div/div[3]/form/div/div[2]/div/input[1]"))
                .click();

        FileInputStream fs = new FileInputStream("D:\\dbankdemo.xlsx");

        Workbook workbook = new XSSFWorkbook(fs);
        Sheet sheet = workbook.getSheet("login");
        Row row1 = sheet.getRow(4);
        String book = row1.getCell(0).getStringCellValue();
        driver.findElement(By.xpath("//*[@id=\"rhf_header_element\"]/nav/div/div[3]/form/div/div[2]/div/input[1]"))
                .sendKeys(book);
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"rhf_header_element\"]/nav/div/div[3]/form/div/span/button"))
                .sendKeys(book);
        String check = driver
                .findElement(By.xpath("//*[@id='searchGrid']/div/section[1]/section[1]/div/div[1]/div[1]/h1/span"))
                .getText();
        Thread.sleep(3000);
        if (check.equals(book)) {
            System.out.println("Thus the search of Chetan Bhagat is true");
        } else {
            System.out.println("The result is not True");
        }
    }

    @Test(priority = 1)
    public void Testcase2() throws Exception {
        driver.navigate().to("https://www.barnesandnoble.com/");
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.xpath("//*[@id='rhfCategoryFlyout_Audiobooks']"))).perform();
        Thread.sleep(4000);
        driver.findElement(
                By.xpath("//*[@id='navbarSupportedContent']/div/ul/li[5]/div/div/div[1]/div/div[2]/div[1]/dd/a[1]"))
                .click();
        Thread.sleep(2000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");
        Thread.sleep(3000);

        driver.findElement(By.linkText("Funny Story")).click();
        Thread.sleep(2000);
        JavascriptExecutor js1 = (JavascriptExecutor) driver;
        js1.executeScript("window.scrollBy(0,500)");
        Thread.sleep(2000);

        driver.findElement(By.xpath("//*[@id=\"otherAvailFormats\"]/div/div/div[3]/a/p/span")).click();
        Thread.sleep(2000);
        JavascriptExecutor js2 = (JavascriptExecutor) driver;
        js2.executeScript("window.scrollBy(0,500)");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"prodInfoContainer\"]/div[3]/form[1]/input[10]")).click();
        Thread.sleep(2000);
        String find = driver.switchTo().alert().getText();
        if (find.contains("Item Successfully Added To Your Cart")) {
            System.out.println("Successfully inserted into the cart");
        } else {
            System.out.println("Item not inserted into the cart");
        }
    }

    @Test(priority = 2)
    public void Testcase3() throws Exception {
        driver.navigate().to("https://www.barnesandnoble.com/");
        JavascriptExecutor js3 = (JavascriptExecutor) driver;
        js3.executeScript("window.scrollBy(0,2000)");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id='footer']/div/dd/div/div/div[1]/div/a[5]/span")).click();
        JavascriptExecutor js4 = (JavascriptExecutor) driver;
        js4.executeScript("window.scrollBy(0,1000)");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"rewards-modal-link\"]")).click();

        WebElement popupLabel = driver.findElement(By.xpath("//div[@class='popup']//label"));
        String labelText = popupLabel.getText();

        if (labelText.contains("Signin or Create account")) {
            System.out.println("Signin or Create Account label found in the popup.");
        
        } else {
            System.out.println("Label does not contain 'Sign in' or 'Create account' text.");
        }

        File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = "CC:\\Users\\91701\\Desktop\\cc2softwaretesting\\screenshot.png";
        FileUtils.copyFile(screen, new File(path));
    }

    @AfterMethod
    public void Testquit() {
        driver.quit();
    }
}
