package Modules;

import Pages.BasePage;
import Pages.LoginPage;
import Pages.ProductsListPage;
import Utils.JsonUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@DisplayName("Products Test Set")
public class ProductsTest {
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
    @DisplayName("Register and delete a product with required values")
    public void registerAProductWithRequiredValues() {
        String username = JsonUtils.getJsonValue("username");
        String password = JsonUtils.getJsonValue("password");

        ProductsListPage productsListPage = new LoginPage(browser)
                .fillUserField(username)
                .fillPasswordField(password)
                .clickLoginButton()
                .accessCreateProductForm()
                .fillProductNameField("iPhone")
                .fillProductValueField("4522.45")
                .fillProductColoursField("Black")
                .clickCreateProductButton()
                .accessProductsList();

        String registeredProductName = productsListPage.getNameFromLastItem();
        String registeredProductValue = productsListPage.getValueFromLastItem();

        Assertions.assertEquals("iPhone", registeredProductName);
        Assertions.assertEquals("R$ 4.522,45", registeredProductValue);

        Integer numberItemsBeforeDeletion = productsListPage.getNumberListItems();
        productsListPage.deleteLastItem();
        Integer numberItemsAfterDeletion = productsListPage.getNumberListItems();
        Integer expectedNumberItemsRegistered = numberItemsBeforeDeletion - 1;

        Assertions.assertEquals(expectedNumberItemsRegistered,numberItemsAfterDeletion);
    }

    @Test
    @DisplayName("Register a product with value 0.00")
    public void registerAProductWithValue000() {
        String username = JsonUtils.getJsonValue("username");
        String password = JsonUtils.getJsonValue("password");

        ProductsListPage productsListPage = new LoginPage(browser)
                .fillUserField(username)
                .fillPasswordField(password)
                .clickLoginButton();

        Integer numberItemsBeforeCreation = productsListPage.getNumberListItems();

        Integer numberItemsAfterCreation = productsListPage.accessCreateProductForm()
                .fillProductNameField("Notebook")
                .fillProductValueField("000")
                .fillProductColoursField("Black")
                .clickCreateProductButtonInvalidData()
                .getNumberListItems();

        Assertions.assertEquals(numberItemsBeforeCreation, numberItemsAfterCreation);
    }

    @Test
    @DisplayName("Register a product with value 0.01")
    public void registerAProductWithValue001() {
        String username = JsonUtils.getJsonValue("username");
        String password = JsonUtils.getJsonValue("password");

        ProductsListPage productsListPage = new LoginPage(browser)
                .fillUserField(username)
                .fillPasswordField(password)
                .clickLoginButton()
                .accessCreateProductForm()
                .fillProductNameField("Tablet")
                .fillProductValueField("001")
                .fillProductColoursField("Black")
                .clickCreateProductButton()
                .accessProductsList();

        String registeredProductName = productsListPage.getNameFromLastItem();
        String registeredProductValue = productsListPage.getValueFromLastItem();

        Assertions.assertEquals("Tablet", registeredProductName);
        Assertions.assertEquals("R$ 0,01", registeredProductValue);
    }

    @Test
    @DisplayName("Register a product with value 7,000.00")
    public void registerAProductWithValue700000() {
        String username = JsonUtils.getJsonValue("username");
        String password = JsonUtils.getJsonValue("password");

        ProductsListPage productsListPage = new LoginPage(browser)
                .fillUserField(username)
                .fillPasswordField(password)
                .clickLoginButton()
                .accessCreateProductForm()
                .fillProductNameField("Notebook")
                .fillProductValueField("7000.00")
                .fillProductColoursField("Black")
                .clickCreateProductButton()
                .accessProductsList();

        String registeredProductName = productsListPage.getNameFromLastItem();
        String registeredProductValue = productsListPage.getValueFromLastItem();

        Assertions.assertEquals("Notebook", registeredProductName);
        Assertions.assertEquals("R$ 7.000,00", registeredProductValue);
    }

    @Test
    @DisplayName("Register a product with value 7,000.01")
    public void registerAProductWithValue700001() {
        String username = JsonUtils.getJsonValue("username");
        String password = JsonUtils.getJsonValue("password");

        ProductsListPage productsListPage = new LoginPage(browser)
                .fillUserField(username)
                .fillPasswordField(password)
                .clickLoginButton();

        Integer numberItemsBeforeCreation = productsListPage.getNumberListItems();

        Integer numberItemsAfterCreation = productsListPage.accessCreateProductForm()
                .fillProductNameField("Notebook")
                .fillProductValueField("7000.01")
                .fillProductColoursField("Black")
                .clickCreateProductButtonInvalidData()
                .getNumberListItems();

        Assertions.assertEquals(numberItemsBeforeCreation, numberItemsAfterCreation);
    }

    // Post actions
    @AfterEach
    public void afterEach() {
        browser.quit();
    }

    // Cleaning test mass
    // @AfterAll
    // TODO: Delete all test mass
}
