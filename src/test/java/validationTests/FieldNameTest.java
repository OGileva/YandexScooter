package validationTests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;
import pages.ScooterForPage;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class FieldNameTest {
    private WebDriver driver;
    private String name;
    private boolean isErrorTextNameVisible;
    private MainPage mainPage;
    private ScooterForPage scooterForPage;


    public FieldNameTest(String name, boolean isErrorTextNameVisible) {
        this.name = name;
        this.isErrorTextNameVisible = isErrorTextNameVisible;
    }
    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"ИванИванИв", false},
                {"Ия", false},
                {"Яна", false},
                {"МарияМарияМари", false},
                {"МарияМарияМария", false},
                {"Анна Мария", false},
                {"Анна-Мария", false},
                {"Я", true},
                {"МарияМарияМарияМарияМарияМария", true},
                {"МарияМарияМарияМ", true},
                {"МарияМарияМарияМа", true},
                {"АлександраАлександраАлександраАлександраАлександраАлександраАлександраАлександраАлександраАлександра", true},
                {"Ivan", true},
                {"1234", true},
                {"$аша", true},
                {" ", true}
        };
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        mainPage = new MainPage(driver);
        scooterForPage = new ScooterForPage(driver);
        driver.manage().window().maximize();
        mainPage.closeCookie();
        mainPage.clickUpOrderButton();
    }
    @Test
    public void NameTest() {
        scooterForPage.fillName(name);
        scooterForPage.clearFocus();
        boolean actualVisibility = scooterForPage.isErrorTextNameVisible();
        assertEquals("Ошибка видимости сообщения для имени: "+name, isErrorTextNameVisible, actualVisibility);
    }
    @After
    public void tearDown() {
        driver.quit();
    }
}
