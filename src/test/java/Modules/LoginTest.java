package Modules;

import Pages.BasePage;
import Pages.LoginPage;
import Utils.JsonUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@DisplayName("Login Test Set")
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
    @DisplayName("Login with valid credentials")
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

    // Post actions
    @AfterEach
    public void afterEach() {
        browser.quit();
    }
}
