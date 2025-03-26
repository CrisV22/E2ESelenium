package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    protected final WebDriver browser;

    @FindBy(css = ".toast.rounded")
    protected WebElement toast;

    public BasePage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
    }

    public void accessHomePage() {
        this.browser.get("http://165.227.93.41/lojinha-web/v2/");
    }
}
