package Modules;

import Pages.BasePage;
import Pages.LoginPage;
import Utils.JsonUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@DisplayName("Login Test Set")
public class LoginTest {
    private WebDriver browser;

    @BeforeEach
    public void beforeEach() {
        this.browser = new ChromeDriver();
        BasePage basePage = new BasePage(browser);
        basePage.accessHomePage();
    }

    @Test
    @DisplayName("Login with valid credential")
    public void loginWithValidCredential() {
        String username = JsonUtils.getJsonValue("username");
        String password = JsonUtils.getJsonValue("password");

        LoginPage loginPage = new LoginPage(browser);
        loginPage.fillUserField(username);
        loginPage.fillPasswordField(password);
        loginPage.clickLoginButton();
    }

    @AfterEach
    public void afterEach() {
        browser.quit();
    }
}
