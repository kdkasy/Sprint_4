package org.example;


import org.example.pageobject.MainPage;
import org.example.pageobject.OrderPage;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


@RunWith(Parameterized.class)
public class OrderTest extends BaseTest {
    private final String button;
    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String phone;

    private final String when;
    private final String duration;
    private final String colour;
    private final String expected;

    public OrderTest(String button, String name, String surname, String address, String metro,
                     String phone, String when, String duration, String colour, String expected) {
        this.button = button;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.when = when;
        this.duration = duration;
        this.colour = colour;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Object[][] getOrderData() {
        return new Object[][] {
                {"Верхняя кнопка", "Иван", "Иванов", "Ленина, 15", "Сокольники", "+79999999999",
                       "04.07.2024", "трое суток", "black", "Заказ оформлен"},
                {"Нижняя кнопка", "Иван", "Иванов", "Ленина, 15", "Черкизовская", "+79999999999",
                        "06.07.2024", "шестеро суток", "gray", "Заказ оформлен"}
        };
    }


    @Test
    public void orderTest() {
        OrderPage orderPage;
        if(button.equals("Верхняя кнопка")){
            orderPage = new MainPage(driver)
                    .clickOnHeaderOrderButton();
        } else {
            orderPage = new MainPage(driver).clickOnLowerOrderButton();
        }
        String actualText = orderPage.fillAbutWhomInfo(name, surname, address, metro, phone)
                .fillRentAndConfirm( when, duration, colour);
        Assert.assertThat("Ожидается подтверждение 'Заказ оформлен'", actualText, CoreMatchers.containsString(expected));
    }
}
