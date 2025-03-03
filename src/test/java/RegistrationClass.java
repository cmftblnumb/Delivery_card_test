import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationClass {
    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://localhost:9999";
        Configuration.timeout = 15000;
        Configuration.headless = true;
    }

    @Test
    void shouldSubmitOrderFormSuccessfully() {
        Selenide.open("http://localhost:9999");

        String planningDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] input").setValue(planningDate);
        $("[data-test-id=name] input").setValue("Иван Иванов");
        $("[data-test-id=phone] input").setValue("+79991234567");
        $("[data-test-id=agreement]").click();
        $(".button__text").click();

        $("[data-test-id=notification]").shouldBe(visible).shouldHave(text("Успешно!"));
    }
}
