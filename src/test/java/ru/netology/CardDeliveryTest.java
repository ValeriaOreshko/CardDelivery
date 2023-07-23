package ru.netology;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {

    @Test
    void shouldTest() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Санкт-Петербург");

        LocalDate currentDate = LocalDate.now();
        LocalDate futureDate = currentDate.plusDays(3);
        String dateText = futureDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        $("[data-test-id='date'] input").setValue("dateText");
        $("[data-test-id='name'] input").setValue("Мария Иванова");
        $("[data-test-id='phone'] input").setValue("+79110000000");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldTestElements() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Мо");
        $(withText("Москва")).click();

        $(".icon").click();

        LocalDate currentDate = LocalDate.now();
        LocalDate futureDate = currentDate.plusWeeks(1);
        Long millis = futureDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();

        while (!$(By.cssSelector("[data-day='" + millis + "']")).exists()) {
            $(By.cssSelector("[data-step='1']")).click();
        }

        $("[data-day='" + millis + "']").click();

        $("[data-test-id='name'] input").setValue("Иван Петров");
        $("[data-test-id='phone'] input").setValue("+79110000000");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
    }

}
