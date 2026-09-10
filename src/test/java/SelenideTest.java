import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class SelenideTest {

    @BeforeAll
    static void beforeAll() {

        //Задаём разрешение браузера
        //Configuration.browserSize= "2560x1440";

        //Пропускаем полную загрузку страницы
        Configuration.pageLoadStrategy = "eager";

        //Оставляем браузер открытым
        Configuration.holdBrowserOpen = true;
    }

    @Test
    public void testIssueSearch() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com");

        $(".HeaderSearch-module__searchSlot__oVOUS").click();
        $("input[aria-label='Search or jump to']")
                .setValue("eroshenkoam/allure-example")
                .pressEnter();

        $(linkText("eroshenkoam/allure-example")).click();

     //   $(linkText("eroshenkoam/allure-example")).click();

      //  $(withText("#80")).should(Condition.exist);

    }
}