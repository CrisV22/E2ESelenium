package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    private final WebDriver browser;

    public BasePage(WebDriver browser) {
        this.browser = browser;
    }

    public void accessHomePage() {
        PageFactory.initElements(browser, this);
        this.browser.get("http://165.227.93.41/lojinha-web/v2/");
    }
}
