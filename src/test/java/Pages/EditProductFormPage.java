package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EditProductFormPage extends BasePage {

    @FindBy(id = "produtonome")
    private WebElement nameField;

    @FindBy(id = "produtovalor")
    private WebElement valueField;

    @FindBy(id = "produtocores")
    private WebElement coloursField;

    @FindBy(id = "btn-salvar")
    private WebElement updateProductButton;

    public EditProductFormPage(WebDriver browser) {
        super(browser);
        PageFactory.initElements(browser, this);
    }

    public EditProductFormPage editProductNameField(String name) {
        nameField.sendKeys(name);
        return this;
    }

    public EditProductFormPage editProductValueField(String value) {
        valueField.sendKeys(value);
        return this;
    }

    public EditProductFormPage editProductColoursField(String colors) {
        coloursField.sendKeys(colors);
        return this;
    }

    public EditProductFormPage clickUpdateProductButton() {
        updateProductButton.click();
        return new EditProductFormPage(browser);
    }
}
