package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class EditProductFormPage extends BasePage {

    @FindBy(id = "produtonome")
    private WebElement nameField;

    @FindBy(id = "produtovalor")
    private WebElement valueField;

    @FindBy(id = "produtocores")
    private WebElement coloursField;

    @FindBy(css = "button[class=\"btn waves-effect waves-light\"]")
    private WebElement updateProductButton;

    @FindBy(css = "a[class=\"waves-effect waves-light btn grey\"]")
    private WebElement productsListButton;

    public EditProductFormPage(WebDriver browser) {
        super(browser);
        PageFactory.initElements(browser, this);
    }

    public EditProductFormPage editProductNameField(String name) {
        nameField.clear();
        wait.until(ExpectedConditions.visibilityOf(nameField)).sendKeys(name);
        return this;
    }

    public EditProductFormPage editProductValueField(String value) {
        valueField.clear();
        wait.until(ExpectedConditions.visibilityOf(valueField)).sendKeys(value);
        return this;
    }

    public EditProductFormPage editProductColoursField(String colors) {
        coloursField.clear();
        wait.until(ExpectedConditions.visibilityOf(coloursField)).sendKeys(colors);
        return this;
    }

    public EditProductFormPage clickEditProductButton() {
        wait.until(ExpectedConditions.visibilityOf(updateProductButton)).click();
        return new EditProductFormPage(browser);
    }

    public ProductsListPage accessProductsList() {
        wait.until(ExpectedConditions.visibilityOf(productsListButton)).click();
        return new ProductsListPage(browser);
    }
}
