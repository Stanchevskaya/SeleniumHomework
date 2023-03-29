package app.tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverTests {

    private static WebDriver driver;

    @BeforeAll
    public static void init(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testGetUrl() {
        driver.navigate().to("https://www.notino.bg/");
        String expected = "Козметика и парфюми онлайн | Парфюмерия notino.bg";
        Assertions.assertEquals(driver.getTitle(), expected);
        Assertions.assertTrue(driver.getCurrentUrl().contains("notino"));
    }

@Test
    public void testInvalidCredentials() {
    driver.navigate().to("https://www.notino.bg/account/login");

    WebElement emailInput = driver.findElement(By.xpath("//input[@name='Email']"));
    emailInput.sendKeys("testdkdlas@blabla.com");

    WebElement passInput = driver.findElement(By.xpath("//input[@id='Password']"));
    passInput.sendKeys("tdhjkd");

    WebElement loginButton = driver.findElement(By.xpath("//button[@name='button']"));
    loginButton.click();

    String actualError = driver.findElement(By.xpath("//*[@id=\"login\"]/div/fieldset/form/fieldset/p[5]/span")).getText();
    String expectedError = "Невалиден вход – неправилен имейл или парола.";

    Assertions.assertEquals(actualError, expectedError);
    driver.close();
    }

@AfterAll
    static void close () {
        driver.quit();
    }

}


