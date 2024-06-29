package org.example.PageObject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class OrderPage extends BasePage{
    //Страница Для кого самокат
    private final By forWhomHeader = By.className("Order_Header__BZXOb");
    //Поле ввода имени
    private final By nameInputField = By.xpath(".//input[@placeholder='* Имя']");
    //private final By name = By.cssSelector(".Input_Input__1iN_Z.Input_Error__1Tx5d.Input_Responsible__1jDKN");
    //Поле ввода фамилии
    private final By surnameInputField = By.xpath(".//input[@placeholder='* Фамилия']");
    //Поле ввода адреса
    private final By addressInputField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    //Поле выбора метро
    private final By metroInputField = By.className("select-search__input");
    //Поле ввода номера телефона
    private final By numberInputField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //Кнопка Далее
    private final By nextButton = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");

    //Страница Про аренду
    private final By rentHeader = By.className("Order_Header__BZXOb");

    //Поле выбора даты
    private final By whenToDeliverField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");

    //Поле выбора срока
    private final By durationInputField = By.className("Dropdown-placeholder");
    //Опции выбора срока
    private final By durationOptions = By.className("Dropdown-option");

    //Чекбокс для цветов самоката черный, серый
    private final By greyColour = By.id("grey");
    private final By blackColour = By.id("black");

    //Кнопка Заказать
    private final By orderButton = By.cssSelector("button[class = 'Button_Button__ra12g Button_Middle__1CSJM']");

    //Окно подтверждения заказа
    private final By confirmDialog = By.className("Order_Modal__YZ-d3");

    //Кнопка оформить заказ "Да"
    private final By confirmButton = By.cssSelector("div[class = 'Order_Modal__YZ-d3'] > div[class = 'Order_Buttons__1xGrp'] > button[class = 'Button_Button__ra12g Button_Middle__1CSJM']");



    public OrderPage(WebDriver driver) {
        super(driver);
    }

    public OrderPage fillAbutWhomInfo(String name, String surname, String address, String metro, String phone){
        wait.until(ExpectedConditions.visibilityOfElementLocated(forWhomHeader));
        driver.findElement(this.nameInputField).sendKeys(name);
        driver.findElement(this.surnameInputField).sendKeys(surname);
        driver.findElement(this.addressInputField).sendKeys(address);
        driver.findElement(this.metroInputField).click();
        driver.findElement(this.metroInputField).sendKeys(metro, Keys.ARROW_DOWN, Keys.ENTER);

        driver.findElement(this.numberInputField).sendKeys(phone);
        driver.findElement(nextButton).click();
        return this;
    }

    public String fillRentAndConfirm(String when, String Duration, String colour){
        wait.until(ExpectedConditions.visibilityOfElementLocated(rentHeader));
        driver.findElement(this.whenToDeliverField).click();
        driver.findElement(this.whenToDeliverField).sendKeys(when, Keys.ENTER);


        driver.findElement(this.durationInputField).click();
        List<WebElement> elements = driver.findElements(durationOptions);
        for(WebElement e : elements){
            if(Duration.equals(e.getText())){
                e.click();
                break;
            }
        }

        if (colour.equals("black")) {
            driver.findElement(blackColour).click();
        }
        else {
            driver.findElement(greyColour).click();
        }

        driver.findElement(orderButton).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmDialog));
        driver.findElement(confirmButton).click();
        return driver.findElement(confirmDialog).getText();
    }
}
