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
public class PhoneNumberFieldValidationTest {
    private WebDriver driver;
    private String phoneNumber;
    private boolean isErrorTextPhoneNumberVisible;
    private MainPage mainPage;
    private ScooterForPage scooterForPage;

    public PhoneNumberFieldValidationTest(String phoneNumber, boolean isErrorTextPhoneNumberVisible) {
        this.phoneNumber = phoneNumber;
        this.isErrorTextPhoneNumberVisible = isErrorTextPhoneNumberVisible;
    }
    @Parameterized.Parameters
    public static Object[][]data() {
        return new Object[][] {
                {"98887776655", false},
                {"+98765432121", false},
                {"+999888777", true},
                {"+9998887776", true},
                {"+82345", true},
                {"+8234512345123451234", true},
                {"+8234512345123451234", true},
                {"+999888777766", true},
                {"+234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890", true},
                {"ИванИванИ", true},
                {"8 9997776655", true},
                {"8-999444332", true},
                {"8%987876554", true},
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
    public void PhoneNumberTest() {
        scooterForPage.fillPhoneNumber(phoneNumber);
        scooterForPage.clearFocus();
        boolean actualVisibility = scooterForPage.isErrorTextPhoneNumberVisible();
        assertEquals("Ошибка текста сообщения для номера: " + phoneNumber, isErrorTextPhoneNumberVisible, actualVisibility);
    }
    @After
    public void tearDown() {
        driver.quit();
    }
}
