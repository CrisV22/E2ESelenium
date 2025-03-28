package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CreateProductFormPage extends BasePage {

    @FindBy(id = "produtonome")
    private WebElement nameField;

    @FindBy(id = "produtovalor")
    private WebElement valueField;

    @FindBy(id = "produtocores")
    private WebElement colorsField;

    @FindBy(id = "btn-salvar")
    private WebElement createProductButton;

    public CreateProductFormPage(WebDriver browser) {
        super(browser);
        PageFactory.initElements(browser, this);
    }

    public CreateProductFormPage fillProductNameField(String name) {
        nameField.sendKeys(name);
        return this;
    }

    public CreateProductFormPage fillProductValueField(String value) {
        valueField.sendKeys(value);
        return this;
    }

    public CreateProductFormPage fillProductColoursField(String colors) {
        colorsField.sendKeys(colors);
        return this;
    }

    public EditProductFormPage clickCreateProductButton() {
        createProductButton.click();
        return new EditProductFormPage(browser);
    }

    public ProductsListPage clickCreateProductButtonInvalidData() {
        createProductButton.click();
        return new ProductsListPage(browser);
    }

    public String getToastMessage() {
        return wait.until(ExpectedConditions.visibilityOf(toast)).getText();
    }

}
