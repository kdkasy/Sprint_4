package org.example.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;



import java.util.List;

public class MainPage extends BasePage {
    public static final String URL = "https://qa-scooter.praktikum-services.ru/";
    //Секция вопросов
    private final By questionsSection = By.className("Home_FAQ__3uVm4");

    //Список вопросов
    private final By questions = By.className("accordion__button");

    //Развернувшийся ответ
    private final By answer = By.xpath("//div[@class='accordion__panel' and not(@hidden)]//p");
    //Кнопка Заказать вверху экрана
    private final By headerOrderButton = By.cssSelector("button.Button_Button__ra12g");

    //Кнопка Заказать в середине
    private final By lowerOrderButton = By.cssSelector("div[class = 'Home_FinishButton__1_cWm'] > button");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public MainPage scrollToQuestions(){

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(questionsSection));
        wait.until(ExpectedConditions.visibilityOfElementLocated(questionsSection));
        return this;
    }

    public String clickOnSelectedQuestion(String question){
        List<WebElement> elements = driver.findElements(questions);
        for(WebElement e: elements){
            if (question.equals(e.getText())){
                e.click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(answer));
                return driver.findElement(answer).getText();
            }
        }
        return null;
    }

    public OrderPage clickOnHeaderOrderButton(){
        driver.findElement(headerOrderButton).click();
        return new OrderPage(driver);
    }

    public OrderPage clickOnLowerOrderButton(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(lowerOrderButton));
        wait.until(ExpectedConditions.visibilityOfElementLocated(lowerOrderButton));
        driver.findElement(lowerOrderButton).click();
        return new OrderPage(driver);
    }

}
