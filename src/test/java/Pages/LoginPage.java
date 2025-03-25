package Pages;

import io.opentelemetry.api.baggage.BaggageEntry;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    @FindBy(id = "usuario")
    private final WebElement usernameField;

    @FindBy(id = "senha")
    private final WebElement passwordField;

    @FindBy(id = "btn-entrar")
    private final WebElement loginButton;

    public LoginPage(WebDriver browser) {
        super(browser);
        this.usernameField = browser.findElement(By.id("usuario"));
        this.passwordField = browser.findElement(By.id("senha"));
        this.loginButton = browser.findElement(By.id("btn-entrar"));
    }

    public LoginPage fillUserField(String username) {
        usernameField.sendKeys(username);
        return this;
    }

    public LoginPage fillPasswordField(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    public ProductsListPage clickLoginButton() {
        loginButton.click();
        return new ProductsListPage(browser);
    }
}
