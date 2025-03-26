package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsListPage extends BasePage {
    @FindBy(id = "logo-container")
    private WebElement title;

    @FindBy(css = "a[class=\"waves-effect waves-light btn right\"]")
    private WebElement addProductButton;

    public ProductsListPage (WebDriver browser) {
        super(browser);
        PageFactory.initElements(browser, this);
    }

    public void accessAddProductForm() {
        addProductButton.click();
    }

    public String getToastMessage() {
        return toast.getText();
    }

    public String getTitleMessage() {
        return title.getText();
    }
}
