package validationTests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;
import pages.ScooterForPage;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class FieldMetroStationTest {
    private WebDriver driver;
    private MainPage mainPage;
    private ScooterForPage scooterForPage;
    private String metroStation;
    private boolean isErrorTextMetroStationVisible;

    public FieldMetroStationTest(String metroStation, boolean isErrorTextMetroStationVisible) {
        this.metroStation = metroStation;
        this.isErrorTextMetroStationVisible = isErrorTextMetroStationVisible;
    }
    @Parameterized.Parameters
    public static Object[][]data() {
        return new Object[][] {
                {"Арбатская", false},
                {"Покрышкина", true},
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
    public void metroStationTest() {
        scooterForPage.setMetroStation(metroStation);
        scooterForPage.clickNextButton();
        boolean actualVisibility = scooterForPage.isErrorTextMetroStationVisible();
        assertEquals("Ошибка отображения сообщения для станции метро: " + metroStation, isErrorTextMetroStationVisible, actualVisibility);
    }
    @After
    public void tearDown() {
        driver.quit();
    }
}
