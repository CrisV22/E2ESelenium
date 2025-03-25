package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductsListPage extends BasePage {
    @FindBy(id = "logo-container")
    private final WebElement title;
    @FindBy(css = "a[class=\"waves-effect waves-light btn right\"]")
    private final WebElement addProductButton;

    public ProductsListPage (WebDriver browser) {
        super(browser);
        this.title = browser.findElement(By.id("logo-container"));
        this.addProductButton = browser.findElement(By.cssSelector("a[class=\"waves-effect waves-light btn right\"]"));
    }

    public void accessAddProductForm() {
        addProductButton.click();
    }

    public String getTitleMessage() {
        return title.getText();
    }
}
