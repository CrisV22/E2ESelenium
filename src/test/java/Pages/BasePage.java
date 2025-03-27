package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import java.time.Duration;

public class BasePage {
    protected final WebDriver browser;

    @FindBy(css = ".toast.rounded")
    protected WebElement toast;
    protected FluentWait<WebDriver> wait;

    public BasePage(WebDriver browser) {
        this.browser = browser;
        PageFactory.initElements(browser, this);
        this.wait = new FluentWait<>(browser)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(Exception.class);
    }

    public void accessHomePage() {
        this.browser.get("http://165.227.93.41/lojinha-web/v2/");
    }
}
