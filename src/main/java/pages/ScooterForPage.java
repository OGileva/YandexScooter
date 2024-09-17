package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ScooterForPage {
    private WebDriver driver;
    private final String ERROR_MESSAGE = "Введите корректное имя";

    public ScooterForPage(WebDriver driver) {
        this.driver = driver;
    }

    //Локаторы формы Для кого самокат
    //поле Имя
    public By nameField = By.xpath("//input[@placeholder='* Имя']");
    //текст ошибки поля Имя
    public By errorTextNameField = By.xpath("//div[text()='Введите корректное имя']");
    //поле Фамилия
    public By surnameField = By.xpath("//input[@placeholder='* Фамилия']");
    //текст ошибки поля Фамилия
    public By errorTextSurnameField = By.xpath("//div[text()='Введите корректную фамилию']");
    //поле Адрес
    public By addressField = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    //текст ошибки поля Адрес
    public By errorTextAddressField = By.xpath("//div[text()='Введите корректный адрес']");
    //поле Станция Метро
    private final By metroStationFiled = By.xpath("//input[@placeholder='* Станция метро']");
    //текст ошибки поля Станция Метро
    public By errorTextMetroStation = By.xpath("//div[text()='Выберите станцию']");
    //поле Телефон
    public By phoneNumberField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    //текст ошибки поля Телефон
    public By errorTextPhoneNumberField = By.xpath("//div[text()='Введите корректный номер']");
    //кнопка Далее
    public By nextButtonAboutRentPage = By.xpath("//button[text()='Далее']");


    //Методы формы Для кого самокат
    //заполнить поле Имя
    public void fillName(String name) {
        driver.findElement(nameField).clear();
        driver.findElement(nameField).sendKeys(name);
    }
    //заполнить поле Фамилия
    public void fillSurname(String surname) {
        driver.findElement(surnameField).clear();
        driver.findElement(surnameField).sendKeys(surname);
    }
    //заполнить поле Адрес
    public void fillAddress(String address) {
        driver.findElement(addressField).clear();
        driver.findElement(addressField).sendKeys(address);
    }
    //заполнение поля Метро
    public void setMetroStation(String metroStationName) {
        driver.findElement(metroStationFiled).click();
        driver.findElement(By.xpath(".//*[text() = '" + metroStationName + "']")).click();
    }

    //заполнить поле Телефон
    public void fillPhoneNumber(String phoneNumber) {
        driver.findElement(phoneNumberField).clear();
        driver.findElement(phoneNumberField).sendKeys(phoneNumber);
    }
    //убрать фокус с поля
    public void clearFocus() {
        driver.findElement(nameField).sendKeys(Keys.TAB);
    }
    //проверить появился ли текст ошибки поля Имя
    public boolean isErrorTextNameVisible() {
        try {
            WebElement errorMessage = driver.findElement(errorTextNameField);
            return errorMessage.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    //проверить появился ли текст ошибки поля Фамилия
    public boolean isErrorTextSurnameVisible() {
        try {
            WebElement errorMessage = driver.findElement(errorTextSurnameField);
            return errorMessage.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    //проверить появился ли текст ошибки поля Адрес
    public boolean isErrorTextAddressVisible() {
        try {
            WebElement errorMessage = driver.findElement(errorTextAddressField);
            return errorMessage.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    //проверить появился ли текст ошибки поля Станция Метро
    public boolean isErrorTextMetroStationVisible() {
        try {
            WebElement errorMessage = driver.findElement(errorTextMetroStation);
            return errorMessage.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    //проверить появилась ли ошибка поля Телефон
    public boolean isErrorTextPhoneNumberVisible() {
        try {
            WebElement errorMessage = driver.findElement(errorTextPhoneNumberField);
            return errorMessage.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    //клик на кнопку Далее
    public void clickNextButton() {
        driver.findElement(nextButtonAboutRentPage).click();
    }
}
