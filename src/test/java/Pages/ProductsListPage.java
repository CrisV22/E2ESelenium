package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductsListPage extends BasePage {
    @FindBy(id = "logo-container")
    private WebElement title;

    @FindBy(css = "a[class=\"waves-effect waves-light btn right\"]")
    private WebElement addProductButton;

    @FindBy(css = ".collection li:last-of-type p")
    private WebElement productValue;

    @FindBy(css = ".collection li:last-of-type a")
    private WebElement productName;

    public ProductsListPage (WebDriver browser) {
        super(browser);
        PageFactory.initElements(browser, this);
    }

    public CreateProductFormPage accessCreateProductForm() {
        wait.until(ExpectedConditions.elementToBeClickable(addProductButton)).click();
        return new CreateProductFormPage(browser);
    }

    public String getNameFromLastItem() {
        return productName.getText();
    }

    public String getValueFromLastItem() {
        return productValue.getText();
    }

    public String getToastMessage() {
        return wait.until(ExpectedConditions.visibilityOf(toast)).getText();
    }

    public String getTitleMessage() {
        return wait.until(ExpectedConditions.visibilityOf(title)).getText();
    }
}
