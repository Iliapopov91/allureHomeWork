import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class StepsTest {

    @BeforeAll
    static void beforeAll() {

        //Задаём разрешение браузера
        //Configuration.browserSize= "2560x1440";

        //Пропускаем полную загрузку страницы
        Configuration.pageLoadStrategy = "eager";

        //Оставляем браузер открытым
        Configuration.holdBrowserOpen = true;
    }

    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final String REPONAME = "allure-example";

    @Test
    public void testLambdaStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", () -> {
            open("https://github.com");
        });
        step("Ищем репозиторий " + REPOSITORY, () -> {
            $(".HeaderSearch-module__searchSlot__oVOUS").click();
            $("input[aria-label='Search or jump to']")
                    .setValue(REPOSITORY)
                    .pressEnter();
        });
        step("Кликаем по ссылке репозитория" + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });
        step("Проверка нахождения в нужном репозитории" + REPONAME, () -> {
            $("a[href='/eroshenkoam/allure-example']")
                    .shouldBe(visible)
                    .shouldHave(text(REPONAME));
        });
    }

        @Test
        public void testAnnotatedStep() {
            SelenideLogger.addListener("allure", new AllureSelenide());
            WebSteps steps = new WebSteps();

            steps.openMainPage();
            steps.searchForRepository(REPOSITORY);
            steps.clickOnRepositoryLink(REPOSITORY);
            steps.validateRepositoryLink(REPONAME);
        }
    }
