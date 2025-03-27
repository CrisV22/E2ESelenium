package Modules;

import Pages.BasePage;
import Pages.LoginPage;
import Pages.ProductsListPage;
import Utils.JsonUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
    @Order(1)
    @DisplayName("Registering a product filling all values with price of 7,000.00")
    public void registeringAProductFillingAllValuesWithPriceOf700000() {
        String username = JsonUtils.getJsonValue("username");
        String password = JsonUtils.getJsonValue("password");

        ProductsListPage productsListPage = new LoginPage(browser)
                .fillUserField(username)
                .fillPasswordField(password)
                .clickLoginButton();

        Integer numberItemsBeforeCreation = productsListPage.getNumberListItems();

        productsListPage.accessCreateProductForm()
                .fillProductNameField("Notebook")
                .fillProductValueField("7000.00")
                .fillProductColoursField("Black")
                .clickCreateProductButton()
                .accessProductsList();

        // Asserting the product was created
        Integer numberItemsAfterCreation = productsListPage.getNumberListItems();
        Integer expectedItemsAfterCreation = numberItemsBeforeCreation + 1;

        String registeredProductName = productsListPage.getNameFromLastItem();
        String registeredProductValue = productsListPage.getValueFromLastItem();

        Assertions.assertEquals(expectedItemsAfterCreation, numberItemsAfterCreation);
        Assertions.assertEquals("Notebook", registeredProductName);
        Assertions.assertEquals("R$ 7.000,00", registeredProductValue);
    }

    @Test
    @Order(2)
    @DisplayName("Should impede duplication of product 7,000.00 in registration")
    public void shouldImpedeDuplicationOfProduct700000InRegistration() {
        String username = JsonUtils.getJsonValue("username");
        String password = JsonUtils.getJsonValue("password");

        ProductsListPage productsListPage = new LoginPage(browser)
                .fillUserField(username)
                .fillPasswordField(password)
                .clickLoginButton();

        Integer numberItemsBeforeCreation = productsListPage.getNumberListItems();

        Integer numberItemsAfterCreation = productsListPage.accessCreateProductForm()
                .fillProductNameField("Notebook")
                .fillProductValueField("7000.00")
                .fillProductColoursField("Black")
                .clickCreateProductButton()
                .accessProductsList()
                .getNumberListItems();

        Assertions.assertEquals(numberItemsBeforeCreation, numberItemsAfterCreation);
    }

    @Test
    @Order(2)
    @DisplayName("Should impede duplication of product 7,000.00 in edition")
    public void shouldImpedeDuplicationOfProduct700000InEdition() {
        String username = JsonUtils.getJsonValue("username");
        String password = JsonUtils.getJsonValue("password");

        ProductsListPage productsListPage = new LoginPage(browser)
                .fillUserField(username)
                .fillPasswordField(password)
                .clickLoginButton();

        Integer numberItemsBeforeCreation = productsListPage.getNumberListItems();

        Integer numberItemsAfterCreation = productsListPage.accessCreateProductForm()
                .fillProductNameField("Notebook")
                .fillProductValueField("7000.00")
                .fillProductColoursField("Black")
                .clickCreateProductButton()
                .accessProductsList()
                .getNumberListItems();

        Assertions.assertEquals(numberItemsBeforeCreation, numberItemsAfterCreation);
    }

    @Test
    @Order(3)
    @DisplayName("Editing all product values")
    public void editingAllproductValues() {
        String username = JsonUtils.getJsonValue("username");
        String password = JsonUtils.getJsonValue("password");

        ProductsListPage productsListPage = new LoginPage(browser)
                .fillUserField(username)
                .fillPasswordField(password)
                .clickLoginButton()
                .accessEditProductForm()
                .editProductNameField("Notebook edited")
                .editProductValueField("3500.00")
                .editProductColoursField("Grey, Blue")
                .clickEditProductButton()
                .accessProductsList();

        String editedProductName = productsListPage.getNameFromLastItem();
        String editedProductValue = productsListPage.getValueFromLastItem();

        // Asserting all the product data was updated
        Assertions.assertEquals("Notebook edited", editedProductName);
        Assertions.assertEquals("R$ 3.500,00", editedProductValue);
    }

    @Test
    @Order(4)
    @DisplayName("Registering and deleting a product with required values with price of 0.01")
    public void registeringAndDeletingAProductWithRequiredValuesWithPriceOf001() {
        String username = JsonUtils.getJsonValue("username");
        String password = JsonUtils.getJsonValue("password");

        ProductsListPage productsListPage = new LoginPage(browser)
                .fillUserField(username)
                .fillPasswordField(password)
                .clickLoginButton();

        Integer numberItemsBeforeCreation = productsListPage.getNumberListItems();

        productsListPage.accessCreateProductForm()
                .fillProductNameField("iPhone")
                .fillProductValueField("001")
                .clickCreateProductButton()
                .accessProductsList();

        // Asserting the product was created
        Integer numberItemsAfterCreation = productsListPage.getNumberListItems();
        Integer expectedItemsAfterCreation = numberItemsBeforeCreation + 1;

        String registeredProductName = productsListPage.getNameFromLastItem();
        String registeredProductValue = productsListPage.getValueFromLastItem();

        Assertions.assertEquals(expectedItemsAfterCreation, numberItemsAfterCreation);
        Assertions.assertEquals("iPhone", registeredProductName);
        Assertions.assertEquals("R$ 0,01", registeredProductValue);

        // Asserting the product was deleted
        Integer numberItemsBeforeDeletion = productsListPage.getNumberListItems();
        productsListPage.deleteLastItem();
        Integer numberItemsAfterDeletion = productsListPage.getNumberListItems();
        Integer expectedNumberItemsRegistered = numberItemsBeforeDeletion - 1;

        Assertions.assertEquals(expectedNumberItemsRegistered,numberItemsAfterDeletion);
    }

    @Test
    @Order(5)
    @DisplayName("Should impede registration of products with value equal or smaller than 0.01")
    public void shouldImpedeRegistrationOfProductsWithValueEqualOrSmallerThan001() {
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
    @Order(6)
    @DisplayName("Should impede registration of products with value equal or bigger than 7,000.01")
    public void shouldImpedeRegistrationOfProductsWithValueEqualOrBiggerThan700001() {
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
