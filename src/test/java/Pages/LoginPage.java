package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {
    @FindBy(id = "usuario")
    private WebElement usernameField;

    @FindBy(id = "senha")
    private WebElement passwordField;

    @FindBy(id = "btn-entrar")
    private WebElement loginButton;

    public LoginPage(WebDriver browser) {
        super(browser);
        PageFactory.initElements(browser, this);
    }

    public LoginPage fillUserField(String username) {
        wait.until(ExpectedConditions.visibilityOf(usernameField)).sendKeys(username);
        return this;
    }

    public LoginPage fillPasswordField(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(password);
        return this;
    }

    public ProductsListPage clickLoginButton() {
        wait.until(ExpectedConditions.visibilityOf(loginButton)).click();
        return new ProductsListPage(browser);
    }
}
