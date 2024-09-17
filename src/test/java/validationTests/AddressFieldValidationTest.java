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
public class AddressFieldValidationTest {
    private WebDriver driver;
    private String address;
    private boolean isErrorTextAddressVisible;
    private MainPage mainPage;
    private ScooterForPage scooterForPage;

    public AddressFieldValidationTest(String address, boolean isErrorTextAddressVisible) {
        this.address = address;
        this.isErrorTextAddressVisible = isErrorTextAddressVisible;
    }
    @Parameterized.Parameters
    public static Object[][]data() {
        return new Object[][] {
                {"Достоевского", false},
                {"ДостоевскогоДостоевскогоДостоевскогоДостоевскогоД", false},
                {"ДостоевскогоДостоевскогоДостоевскогоДостоевскогоДо", false},
                {"Арбат 5", false},
                {"Немировича Данченко", false},
                {"Сибиряков-Гвардейцев", false},
                {"Разина.Иванова", false},
                {"Арбат,", false},
                {"А", true},
                {"Ая5", true},
                {"Аааа", true},
                {"ДостоевскогоДостоевскогоДостоевскогоДостоевскогоДоДостоевскогоДостоевскогоДостоевскогоДостоевскогоДо", true},
                {"ДостоевскогоДостоевскогоДостоевскогоДостоевскогоДос", true},
                {"ДостоевскогоДостоевскогоДостоевскогоДостоевскогоДост", true},
                {"Razina", true},
                {"Репи%на", true},
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
    public void addressTest() {
        scooterForPage.fillAddress(address);
        scooterForPage.clearFocus();
        boolean actualVisibility = scooterForPage.isErrorTextAddressVisible();
        assertEquals("Ошибка отображения сообщения для адреса: " + address, isErrorTextAddressVisible, actualVisibility);
    }
    @After
    public void tearDown() {
        driver.quit();
    }
}
