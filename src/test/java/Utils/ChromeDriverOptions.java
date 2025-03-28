package Utils;

import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverOptions {
    public static ChromeOptions getChromeDriverOptions() {
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-extensions");
        options.addArguments("--blink-settings=imagesEnabled=false");

        return options;
    }
}
