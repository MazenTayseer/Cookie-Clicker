import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class CookieClicker {

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://orteil.dashnet.org/cookieclicker/");
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        WebElement lang = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("langSelect-EN")));
        lang.click();

        Thread.sleep(5000);

        driver.findElement(By.xpath("//a[@class='cc_btn cc_btn_accept_all']")).click();

        new Thread(()->{
            while(true){
                WebElement cookie = driver.findElement(By.id("bigCookie"));
                cookie.click();
            }
        }).start();

        new Thread(()->{
            while (true) {
                List<WebElement> upgrades = driver.findElements(By.xpath("//div[@class='crate upgrade enabled']"));
                if(!upgrades.isEmpty()) {
                    int randomNum = ThreadLocalRandom.current().nextInt(0, upgrades.size());
                    upgrades.get(randomNum).click();
                }

                List<WebElement> items = driver.findElements(By.xpath("//div[@class='product unlocked enabled']"));
                if(!items.isEmpty()) {
                    items.get(items.size()-1).click();
                }

            }
        }).start();



    }

}
