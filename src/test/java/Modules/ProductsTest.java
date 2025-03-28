package Modules;

import Pages.BasePage;
import Pages.LoginPage;
import Pages.ProductsListPage;
import Utils.ChromeDriverOptions;
import Utils.JsonUtils;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Epic("Products management")
public class ProductsTest {
    private WebDriver browser;

    // Arrange
    @BeforeAll
    public void beforeAll() {
        this.browser = new ChromeDriver(ChromeDriverOptions.getChromeDriverOptions());

        BasePage basePage = new BasePage(browser);
        basePage.accessHomePage();

        String username = JsonUtils.getJsonValue("username");
        String password = JsonUtils.getJsonValue("password");

        ProductsListPage productsListPage = new LoginPage(browser)
                .fillUserField(username)
                .fillPasswordField(password)
                .clickLoginButton();

        Integer numberItemsFirstLogin = productsListPage.getNumberListItems();

        if (numberItemsFirstLogin != 0) {
            for (int i = 0; i < numberItemsFirstLogin; i++) {
                productsListPage.deleteLastItem();
            }
        }

        browser.quit();
    }

    @BeforeEach
    public void beforeEach() {
        this.browser = new ChromeDriver(ChromeDriverOptions.getChromeDriverOptions());

        BasePage basePage = new BasePage(browser);
        basePage.accessHomePage();
    }

    // Act and Assert
    @Test
    @Order(1)
    @Story("Should register a product filling all values with price of 7,000.00")
    @Severity(SeverityLevel.BLOCKER)
    public void shouldRegisterAProductFillingAllValuesWithPriceOf700000() {
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
    @Story("Should impede duplication of product 7,000.00 in registration")
    @Severity(SeverityLevel.NORMAL)
    public void shouldImpedeDuplicationOfProduct700000InRegistration() {
        String username = JsonUtils.getJsonValue("username");
        String password = JsonUtils.getJsonValue("password");

        ProductsListPage productsListPage = new LoginPage(browser)
                .fillUserField(username)
                .fillPasswordField(password)
                .clickLoginButton();

        Integer numberItemsBeforeCreation = productsListPage.getNumberListItems();

        // Asserting the product wasn't created due to duplication
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
    @Story("Should edit all product values")
    @Severity(SeverityLevel.CRITICAL)
    public void shouldEditAllproductValues() {
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
    @Story("Should impede duplication of product 3,500.00 in edition")
    @Severity(SeverityLevel.NORMAL)
    public void shouldImpedeDuplicationOfProduct350000InEdition() {
        String username = JsonUtils.getJsonValue("username");
        String password = JsonUtils.getJsonValue("password");

        ProductsListPage productsListPage = new LoginPage(browser)
                .fillUserField(username)
                .fillPasswordField(password)
                .clickLoginButton();

        Integer numberItemsBeforeCreation = productsListPage.getNumberListItems();

        Integer numberItemsAfterCreation = productsListPage.accessCreateProductForm()
                .fillProductNameField("Notebook")
                .fillProductValueField("001")
                .fillProductColoursField("Black")
                .clickCreateProductButton()
                .accessProductsList()
                .getNumberListItems();

        Integer expectedNumberItemsAfterCreation = numberItemsBeforeCreation + 1;
        Assertions.assertEquals(expectedNumberItemsAfterCreation, numberItemsAfterCreation);

        // Editing to cause duplication
        productsListPage
                .accessEditProductForm()
                .editProductNameField("Notebook edited")
                .editProductValueField("3500.00")
                .clickEditProductButton()
                .accessProductsList();

        String editedProductName = productsListPage.getNameFromLastItem();
        String editedProductValue = productsListPage.getValueFromLastItem();

        // Asserting the product data wasn't updated
        Assertions.assertEquals("Notebook", editedProductName);
        Assertions.assertEquals("R$ 0,01", editedProductValue);
    }

    @Test
    @Story("Registering and deleting a product with required values with price of 0.01")
    @Severity(SeverityLevel.BLOCKER)
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
    @Story("Should impede registration of products with value equal or smaller than 0.01")
    @Severity(SeverityLevel.NORMAL)
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
    @Story("Should impede registration of products with value equal or bigger than 7,000.01")
    @Severity(SeverityLevel.NORMAL)
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
    @AfterAll
    public void afterAll() {
        beforeAll();
    }
}
