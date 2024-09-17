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
public class SurnameFieldValidationTest {
    private WebDriver driver;
    private String surname;
    private boolean isErrorTextSurnameVisible;
    private MainPage mainPage;
    private ScooterForPage scooterForPage;

    public SurnameFieldValidationTest(String surname, boolean isErrorTextSurnameVisible) {
        this.surname = surname;
        this.isErrorTextSurnameVisible = isErrorTextSurnameVisible;
    }
    @Parameterized.Parameters
    public static Object[][]data() {
        return new Object[][] {
                {"ПетровПетр", false},
                {"Ая", false},
                {"Отт", false},
                {"ПетровПетровПе", false},
                {"ПетровПетровПет", false},
                {"ПетровПетровПетр", true},
                {"ПетровПетровПетро", true},
                {"ПетровПетровПетровПетровПетров", true},
                {"ДолгоруковДолгоруковДолгоруковДолгоруковДолгоруковДолгоруковДолгоруковДолгоруковДолгоруковДолгоруков", true},
                {"Petrov", true},
                {"Иванов Петров", true},
                {"Иванов-Петров", true},
                {"1234", true},
                {"%Зайцев", true},
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
    public void surnameTest() {
        scooterForPage.fillSurname(surname);
        scooterForPage.clearFocus();
        boolean actualVisibility = scooterForPage.isErrorTextSurnameVisible();
        assertEquals("Ошибка видимости сообщения для фамилии: "+ surname, isErrorTextSurnameVisible, actualVisibility);
    }
    @After
    public void tearDown() {
        driver.quit();
    }
}
