package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //Локаторы главной страницы
    //логотип Яндекс Самокат
    private By scooterLogo = By.xpath("//img[@alt='Scooter']");
    //кнопка заказать вверху страницы
    private By upOrderButton = By.xpath("//button[@class='Button_Button__ra12g']");
    //кнопка Статус заказа
    private By orderStatusButton = By.xpath("//button[@class='Header_Link__1TAG7']");
    //кнопка Статус заказа внизу страницы
    private By downOrderButton = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    //кнопка Все привыкли
    private By closeCookieButton = By.id("rcc-confirm-button");

    //Методы главной страницы
    //нажать на кнопку Заказать вверху страницы
    public void clickUpOrderButton() {
        driver.findElement(upOrderButton).click();
    }
    //закрыть куки
    public void closeCookie() {
        driver.findElement(closeCookieButton).click();
    }

}
