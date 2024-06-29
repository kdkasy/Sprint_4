package org.example;

import org.example.PageObject.MainPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class QuestionsTest extends BaseTest{

    private final String question;
    private final String answer;

    public QuestionsTest(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    @Parameterized.Parameters
    public static Object[][] getCities() {
        return new Object[][] {
                {"Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {"Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."}
        };
    }


    @Test
    public void questionsTest() {
        String actualAnswer =  new MainPage(driver)
                .scrollToQuestions()
                .clickOnSelectedQuestion(question);
        assertEquals("Ответы должны совпадать", answer, actualAnswer);
    }
}