package Modules;

import Pages.BasePage;
import Pages.LoginPage;
import Utils.JsonUtils;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@Epic("Login")
public class LoginTest {
    private WebDriver browser;

    // Arrange
    @BeforeEach
    public void beforeEach() {
        this.browser = new ChromeDriver();
        BasePage basePage = new BasePage(browser);
        basePage.accessHomePage();
    }

    // Act and Assert
    @Test
    @Story("Login with valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    public void loginWithValidCredentials() {
        String username = JsonUtils.getJsonValue("username");
        String password = JsonUtils.getJsonValue("password");

        String message = new LoginPage(browser)
                .fillUserField(username)
                .fillPasswordField(password)
                .clickLoginButton()
                .getTitleMessage();

        Assertions.assertEquals("Lojinha", message);
    }

    @Test
    @Story("Login with invalid credentials")
    @Severity(SeverityLevel.CRITICAL)
    public void loginWithInvalidCredentials() {
        String message = new LoginPage(browser)
                .fillUserField("invalid")
                .fillPasswordField("invalid")
                .clickLoginButton()
                .getToastMessage();

        // Translation: Failed to login
        Assertions.assertEquals("Falha ao fazer o login", message);
    }

    // Post actions
    @AfterEach
    public void afterEach() {
        browser.quit();
    }
}
